package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureFromTemplateGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureFromTemplateResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateProcedureFromTemplateGIResponse response =
        exchange.getIn().getBody(CreateProcedureFromTemplateGIResponse.class);

    CreateProcedureFromTemplateResponseDTO objectResponse =
        wandleCreateProcedureFromTemplateGIZuCreateProcedureFromTemplateDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static CreateProcedureFromTemplateResponseDTO
      wandleCreateProcedureFromTemplateGIZuCreateProcedureFromTemplateDTO(
          CreateProcedureFromTemplateGIResponse objectResponse) {
    return CreateProcedureFromTemplateResponseDTO.builder()
        .objid(objectResponse.getObjid())
        .objname(objectResponse.getObjname())
        .build();
  }
}
