/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.vorgang.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateProcedureResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final CreateProcedureGIResponse response =
        exchange.getIn().getBody(CreateProcedureGIResponse.class);
    handleResponse(
        exchange,
        response.getStatus(),
        response.getErrormessage(),
        response.getObjname(),
        response.getObjid());
  }
}
