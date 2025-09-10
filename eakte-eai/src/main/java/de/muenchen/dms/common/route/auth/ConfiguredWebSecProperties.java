/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2019
 */
package de.muenchen.dms.common.route.auth;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Properties für die Security. Die Einstellungen werden aus den Properties mit entsprechendem
 * Prefix geladen.
 *
 * @author sebastian.schoebel
 */
@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "dms.auth")
public class ConfiguredWebSecProperties {

  /** Benutzer die für den Zugriff berechtigt werden. */
  @Getter @Setter private List<User> users = new ArrayList<>();

  /**
   * Mapping von Rechten auf Rollen.<br>
   * Key: Rolle<br>
   * Value: Liste mit Rechten
   */
  @Getter @Setter private Map<String, Set<String>> roles = new HashMap<>();
}
