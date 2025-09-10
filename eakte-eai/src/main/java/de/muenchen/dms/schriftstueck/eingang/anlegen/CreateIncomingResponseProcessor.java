/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * Diese Klasse wandelt eine Antwort des DMS zur Anlage eines Eingangsdokuments in eine Antwort über
 * die EAI-Anwendung um. Die Anfrage muss eine Vorgangs-ID enthalten.
 */
@Slf4j
@Component
public class CreateIncomingResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final CreateIncomingGIResponse response =
        exchange.getIn().getBody(CreateIncomingGIResponse.class);

    final CreateIncomingAntwortDTO objectResponse =
        CreateIncomingAntwortDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    objectResponse.setObjname(response.getObjname());
    objectResponse.setObjid(response.getObjid());
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
