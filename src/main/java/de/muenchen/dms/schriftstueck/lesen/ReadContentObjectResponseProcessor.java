/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.lesen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGIAttachementTypeZuAttachementReferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadContentObjectResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final ReadContentObjectGIResponse response =
        exchange.getIn().getBody(ReadContentObjectGIResponse.class);

    final ReadContentObjectResponseDTO objectResponse = wandleReadContentObjectGIZuDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadContentObjectResponseDTO wandleReadContentObjectGIZuDTO(
      final ReadContentObjectGIResponse response) {
    return ReadContentObjectResponseDTO.builder()
        .referrednumber(response.getReferrednumber())
        .giattachmenttype(
            wandleGIAttachementTypeZuAttachementReferenzen(response.getGiattachmenttype()))
        .build();
  }
}
