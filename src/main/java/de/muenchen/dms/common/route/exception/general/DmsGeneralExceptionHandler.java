/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.general;

import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.exception.DmsExceptionHandler;
import org.apache.camel.builder.RouteBuilder;

public class DmsGeneralExceptionHandler implements DmsExceptionHandler {

  @Override
  public void handleException(RouteBuilder builder) {
    builder
        .onException(Exception.class)
        .to(RouteConstants.HANDLE_GENERAL_EXCEPTION_ENDPOINT)
        .handled(true)
        .end();
  }
}
