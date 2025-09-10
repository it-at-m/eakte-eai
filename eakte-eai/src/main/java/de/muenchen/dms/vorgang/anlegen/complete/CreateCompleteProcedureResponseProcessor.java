package de.muenchen.dms.vorgang.anlegen.complete;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(Exchange exchange) throws Exception {
    CreateCompleteProcedureGIResponse response =
        exchange.getIn().getBody(CreateCompleteProcedureGIResponse.class);

    CreateCompleteProcedureResponseDTO objectResponse =
        wandleCompleteProcedureGIZuCompleteProcedureDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  private CreateCompleteProcedureResponseDTO wandleCompleteProcedureGIZuCompleteProcedureDTO(
      CreateCompleteProcedureGIResponse objectResponse) {
    return CreateCompleteProcedureResponseDTO.builder()
        .objidprocedure(objectResponse.getObjidprocedure())
        .objnameprocedure(objectResponse.getObjnameprocedure())
        .objiddocument(objectResponse.getObjiddocument())
        .objnamedocument(objectResponse.getObjnamedocument())
        .objidcontent(objectResponse.getObjidcontent())
        .objnamecontent(objectResponse.getObjnamecontent())
        .build();
  }
}
