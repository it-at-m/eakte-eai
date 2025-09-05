/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.general;

import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.MediaType;

public class DmsGeneralExceptionRouteBuilder extends RouteBuilder {
  private final DmsGeneralExceptionProcessor exceptionProcessor;

  public DmsGeneralExceptionRouteBuilder(DmsGeneralExceptionProcessor exceptionProcessor) {
    this.exceptionProcessor = exceptionProcessor;
  }

  @Override
  public void configure() throws Exception {
    from(RouteConstants.HANDLE_GENERAL_EXCEPTION_ENDPOINT)
        .routeId(RouteConstants.EXCEPTION_OCCURRED_ID)
        .to(RouteConstants.LOG_EXCEPTION_ENDPOINT)
        .removeHeader("*")
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
        .process(exceptionProcessor)
        .marshal()
        .json();
  }
}
