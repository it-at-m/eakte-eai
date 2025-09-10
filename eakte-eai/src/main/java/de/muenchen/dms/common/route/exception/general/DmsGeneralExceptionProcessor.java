/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common.route.exception.general;

import static de.muenchen.dms.common.route.exception.ExceptionResponses.createInternalErrorResponse;
import static de.muenchen.dms.common.route.exception.ExceptionResponses.getMessageFrom;
import static de.muenchen.dms.common.route.exception.ExceptionResponses.setResponse;

import de.muenchen.dms.common.model.InternalErrors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.cxf.transport.http.HTTPException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public class DmsGeneralExceptionProcessor implements Processor {

  @Override
  public void process(Exchange exchange) throws Exception {
    // Remove all header.
    exchange.getIn().removeHeaders("*");
    // set default content type
    exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    if (!handleHttpOperationFailedException(exchange)) {
      Throwable throwable = exchange.getProperty(Exchange.EXCEPTION_CAUGHT, Throwable.class);
      if (throwable.getCause() instanceof HTTPException httpEx) {
        setResponse(
            exchange,
            httpEx.getResponseCode(),
            httpEx.getResponseMessage(),
            createInternalErrorResponse(
                InternalErrors.DMS_CONNECTION_ERROR, getMessageFrom(httpEx)));
      } else {
        setResponse(
            exchange,
            HttpStatus.INTERNAL_SERVER_ERROR.value(),
            null,
            createInternalErrorResponse(
                InternalErrors.STABILITY_AT_RISK, getMessageFrom(throwable)));
      }
    }
    // Remove authorization header, otherwise the token would be handed back to the caller in an
    // error case
    exchange.getIn().removeHeader(HttpHeaders.AUTHORIZATION);
  }

  private static boolean handleHttpOperationFailedException(Exchange exchange) {
    HttpOperationFailedException httpException =
        exchange.getProperty(Exchange.EXCEPTION_CAUGHT, HttpOperationFailedException.class);
    if (httpException != null) {
      exchange.getIn().getHeaders().putAll(httpException.getResponseHeaders());
      setResponse(
          exchange,
          httpException.getHttpResponseCode(),
          httpException.getHttpResponseStatus(),
          createInternalErrorResponse(
              InternalErrors.DMS_CONNECTION_ERROR, getMessageFrom(httpException)));
    }
    return httpException != null;
  }
}
