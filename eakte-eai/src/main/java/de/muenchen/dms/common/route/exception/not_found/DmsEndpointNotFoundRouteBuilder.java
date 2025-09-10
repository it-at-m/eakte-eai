/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.not_found;

import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class DmsEndpointNotFoundRouteBuilder extends RouteBuilder {

  private final DmsEndpointNotFoundProcessor processor;

  public DmsEndpointNotFoundRouteBuilder(DmsEndpointNotFoundProcessor processor) {
    this.processor = processor;
  }

  @Override
  public void configure() throws Exception {
    from(RouteConstants.HANDLE_ENDPOINT_NOT_FOUND_EXCEPTION_ENDPOINT)
        .routeId(RouteConstants.ENDPOINT_NOT_FOUND_EXCEPTION_ID)
        .to(RouteConstants.LOG_EXCEPTION_ENDPOINT)
        .removeHeader("*")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.NOT_FOUND.value()))
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.APPLICATION_JSON_VALUE))
        .process(processor)
        .marshal()
        .json();
  }
}
