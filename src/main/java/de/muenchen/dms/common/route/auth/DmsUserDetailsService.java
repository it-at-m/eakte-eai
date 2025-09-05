/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2019
 */
package de.muenchen.dms.common.route.auth;

import java.util.List;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Service laden von Benutzerdaten die die EAI benutzen dürfen.
 *
 * @author sebastian.schoebel
 */
public class DmsUserDetailsService
    implements org.springframework.security.core.userdetails.UserDetailsService {

  private static final Logger LOG = LoggerFactory.getLogger(DmsUserDetailsService.class);

  private static final String BENUTZERFEHLT_TEMPLATE =
      "Benutzer %s ist unvollständig konfiguriert - Username fehlt";
  private static final String ROLLENUNVOLLSTAENDIG_TEMPLATE =
      "Benutzer %s ist unvollständig konfiguriert - Rollen fehlen";

  @Getter @Setter
  private ConfiguredWebSecProperties webSecProperties = new ConfiguredWebSecProperties();

  public DmsUserDetailsService(ConfiguredWebSecProperties webSecProperties) {
    this.webSecProperties = webSecProperties;
  }

  /**
   * {@inheritDoc}<br>
   * <br>
   * Eine {@link UsernameNotFoundException} wird geworfen wenn es keine Benutzer zu dem Namen gibt,
   * der Benutzer kein Passwort hat, der Benutzer keine Rollen hat oder basierend auf den Rollen
   * keine Rechte gesetzt wuerden.
   */
  @Override
  public UserDetails loadUserByUsername(String username) {
    if (webSecProperties.getUsers() == null) {
      throw new UsernameNotFoundException(String.format(BENUTZERFEHLT_TEMPLATE, username));
    }
    if (webSecProperties.getRoles() == null) {
      throw new UsernameNotFoundException(String.format(ROLLENUNVOLLSTAENDIG_TEMPLATE, username));
    }

    final User user =
        webSecProperties.getUsers().stream()
            .filter(u -> username.equals(u.getUsername()))
            .findFirst()
            .orElse(null);

    if (user == null) {
      LOG.warn("Benutzer {} nicht in Konfiguration vorhanden", username);
      throw new UsernameNotFoundException(String.format(BENUTZERFEHLT_TEMPLATE, username));
    }

    if (user.getPassword() == null) {
      LOG.warn("Benutzer {} hat keine Passwort oder fehlt in der Konfiguration", username);
      throw new UsernameNotFoundException(String.format(BENUTZERFEHLT_TEMPLATE, username));
    }

    List<SimpleGrantedAuthority> authorities = null;
    authorities =
        user.getRoles().stream()
            .map(role -> webSecProperties.getRoles().get(role))
            .flatMap(Set::stream)
            .distinct()
            .map(SimpleGrantedAuthority::new)
            .toList();
    if (authorities == null || authorities.isEmpty()) {
      LOG.warn("Benutzer {} hat keine Rollen oder sie fehlen in der Konfiguration", username);
      throw new UsernameNotFoundException(String.format(ROLLENUNVOLLSTAENDIG_TEMPLATE, username));
    }

    LOG.debug("User geladen > {}", user);
    return new org.springframework.security.core.userdetails.User(
        username, user.getPassword(), authorities);
  }
}
