/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateOutgoingGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateOutgoingResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateOutgoingGIResponse response = exchange.getIn().getBody(CreateOutgoingGIResponse.class);

    final CreateOutgoingAntwortDTO objectResponse =
        CreateOutgoingAntwortDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    objectResponse.setObjname(response.getObjname());
    objectResponse.setObjid(response.getObjid());
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
