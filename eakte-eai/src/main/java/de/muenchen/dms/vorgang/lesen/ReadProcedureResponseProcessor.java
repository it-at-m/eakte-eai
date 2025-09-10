/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.vorgang.lesen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import de.muenchen.dms.common.util.Umwandlungen;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadProcedureResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final ReadProcedureGIResponse response =
        exchange.getIn().getBody(ReadProcedureGIResponse.class);
    final ReadProcedureResponseDTO objectResponse = mapReadProcedureGItoVorgangDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  private ReadProcedureResponseDTO mapReadProcedureGItoVorgangDTO(
      final ReadProcedureGIResponse response) {
    return ReadProcedureResponseDTO.builderVorgangLesen()
        .referrednumber(response.getReferrednumber())
        .objterms(response.getObjterms())
        .shortname(response.getShortname())
        .objname(response.getObjname())
        .fileruntimefrom(Umwandlungen.wandleZuOffsetDateTime(response.getFileruntimefrom()))
        .fileruntimetill(Umwandlungen.wandleZuOffsetDateTime(response.getFileruntimetill()))
        .filesubj(response.getFilesubj())
        .accdef(response.getAccdef())
        .objdocstate(response.getObjdocstate())
        .objowngroup(response.getObjowngroup())
        .procremark(response.getProcremark())
        .filetype(response.getFiletype())
        .bostate(response.getBostate())
        .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
        .build();
  }
}
