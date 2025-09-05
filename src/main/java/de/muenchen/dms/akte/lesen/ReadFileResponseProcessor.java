/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.akte.lesen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;
import static de.muenchen.dms.common.util.Umwandlungen.wandleZuOffsetDateTime;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadFileGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadFileResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final ReadFileGIResponse response = exchange.getIn().getBody(ReadFileGIResponse.class);
    final ReadFileResponseDTO objectResponse = wandleReadFileGIZuAkteDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadFileResponseDTO wandleReadFileGIZuAkteDTO(final ReadFileGIResponse response) {

    return ReadFileResponseDTO.builderReadFile()
        .objterms(response.getObjterms())
        .shortname(response.getShortname())
        .objname(response.getObjname())
        .fileruntimefrom(wandleZuOffsetDateTime(response.getFileruntimefrom()))
        .fileruntimetill(wandleZuOffsetDateTime(response.getFileruntimetill()))
        .filesubj(response.getFilesubj())
        .fileouobj(response.getFileouobj())
        .procedureaccdef(response.getProcedureaccdef())
        .accdef(response.getAccdef())
        .apentry(response.getApentry())
        .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
        .build();
  }
}
