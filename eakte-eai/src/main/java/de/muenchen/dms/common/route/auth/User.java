/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2019
 */
package de.muenchen.dms.common.route.auth;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Benutzerobjekt für Authentifizierung
 *
 * @author sebastian.schoebel
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
  private String username;
  private String password;
  private List<String> roles;
  private String serviceUrl;
}
