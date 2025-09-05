/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.auth;

import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.builder.RouteConfigurationBuilder;

public class DmsAuthenticationRouteBuilder extends RouteConfigurationBuilder {
  @Override
  public void configuration() throws Exception {
    rest()
        .securityDefinitions()
        .basicAuth(
            RouteConstants.BASIC_AUTH_SECURITY_DEFINITION,
            "This API uses Basic Auth flow. [More info](https://api.example.com/docs/auth)")
        .end();
  }
}
