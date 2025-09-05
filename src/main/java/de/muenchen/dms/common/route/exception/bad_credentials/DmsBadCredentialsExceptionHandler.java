/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.bad_credentials;

import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.exception.DmsExceptionHandler;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.security.authentication.BadCredentialsException;

public class DmsBadCredentialsExceptionHandler implements DmsExceptionHandler {

  @Override
  public void handleException(RouteBuilder builder) {
    builder
        .onException(BadCredentialsException.class)
        .handled(true)
        .to(RouteConstants.HANDLE_BAD_CREDENTIALS_EXCEPTION_ENDPOINT);
  }
}
