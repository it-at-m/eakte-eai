/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.bad_credentials;

import static de.muenchen.dms.common.route.RestOperationDefinitionFactory.BAD_CREDENTIALS_MSG;

import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class DmsBadCredentialsRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from(RouteConstants.HANDLE_BAD_CREDENTIALS_EXCEPTION_ENDPOINT)
        .routeId(RouteConstants.ENDPOINT_BAD_CREDENTIALS_ID)
        .to(RouteConstants.LOG_EXCEPTION_ENDPOINT)
        .removeHeader("*")
        .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(HttpStatus.UNAUTHORIZED.value()))
        .setHeader(Exchange.CONTENT_TYPE, constant(MediaType.TEXT_PLAIN))
        .transform(constant(BAD_CREDENTIALS_MSG));
  }
}
