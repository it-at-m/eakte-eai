package de.muenchen.dms.common.route.auth;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserService {

  private static final String BENUTZERFEHLT_TEMPLATE =
      "Benutzer %s ist unvollständig konfiguriert - Username fehlt";

  private static final String ROLLENUNVOLLSTAENDIG_TEMPLATE =
      "Benutzer %s ist unvollständig konfiguriert - Rollen fehlen";

  private final ConfiguredWebSecProperties webSecProperties;

  public UserService(ConfiguredWebSecProperties configuredWebSecProperties) {
    this.webSecProperties = configuredWebSecProperties;
  }

  public User loadUserByName(String username) {
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
      log.warn("Benutzer {} nicht in Konfiguration vorhanden", username);
      throw new UsernameNotFoundException(String.format(BENUTZERFEHLT_TEMPLATE, username));
    }

    if (user.getPassword() == null) {
      log.warn("Benutzer {} hat keine Passwort oder fehlt in der Konfiguration", username);
      throw new UsernameNotFoundException(String.format(BENUTZERFEHLT_TEMPLATE, username));
    }
    return user;
  }

  public String getCustomServiceUrlByUsernameOrNull(final String username) {
    final User user = loadUserByName(username);
    if (user.getServiceUrl() != null && !user.getServiceUrl().isEmpty()) {
      return user.getServiceUrl();
    }
    return null;
  }
}
