/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception;

import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;

public class DmsLogExceptionRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(RouteConstants.LOG_EXCEPTION_ENDPOINT)
        .log(LoggingLevel.ERROR, "${exception.message}")
        .log(LoggingLevel.ERROR, "${exception.stacktrace}");
  }
}
