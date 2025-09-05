package de.muenchen.dms.vorgang.anlegen.complete.businessdata;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureBusinessDataGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureBusinessDataResponseProcessor
    extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CreateCompleteProcedureBusinessDataGIResponse response =
        exchange.getIn().getBody(CreateCompleteProcedureBusinessDataGIResponse.class);

    final CreateCompleteProcedureBusinessDataResponseDTO objectDTO =
        wandleCreateCompleteProcedureBusinessDataGIZuDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectDTO);
  }

  private CreateCompleteProcedureBusinessDataResponseDTO
      wandleCreateCompleteProcedureBusinessDataGIZuDTO(
          CreateCompleteProcedureBusinessDataGIResponse response) {
    return CreateCompleteProcedureBusinessDataResponseDTO.builder()
        .objidprocedure(response.getObjidprocedure())
        .objnameprocedure(response.getObjnameprocedure())
        .objiddocument(response.getObjiddocument())
        .objnamedocument(response.getObjnamedocument())
        .objidcontent(response.getObjidcontent())
        .objnamecontent(response.getObjnamecontent())
        .build();
  }
}
