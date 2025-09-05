/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.not_found;

import static de.muenchen.dms.common.route.exception.ExceptionResponses.createInternalErrorResponse;
import static de.muenchen.dms.common.route.exception.ExceptionResponses.getMessageFrom;

import de.muenchen.dms.common.model.InternalErrors;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class DmsEndpointNotFoundProcessor implements Processor {

  @Override
  public void process(Exchange exchange) throws Exception {
    Exception ex = exchange.getException();

    exchange
        .getIn()
        .setBody(createInternalErrorResponse(InternalErrors.NOT_FOUND, getMessageFrom(ex)));
  }
}
