/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.not_found;

import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.exception.DmsExceptionHandler;
import java.io.InterruptedIOException;
import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import org.apache.camel.builder.RouteBuilder;

public class DmsEndpointNotFoundExceptionHandler implements DmsExceptionHandler {

  @Override
  public void handleException(RouteBuilder builder) {
    builder
        .onException(
            ConnectException.class,
            InterruptedIOException.class,
            SocketException.class,
            UnknownHostException.class)
        .handled(true)
        .to(RouteConstants.HANDLE_ENDPOINT_NOT_FOUND_EXCEPTION_ENDPOINT)
        .end();
  }
}
