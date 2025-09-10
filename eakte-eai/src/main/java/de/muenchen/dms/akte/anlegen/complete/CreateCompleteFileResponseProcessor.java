package de.muenchen.dms.akte.anlegen.complete;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteFileGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteFileResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateCompleteFileGIResponse response =
        exchange.getIn().getBody(CreateCompleteFileGIResponse.class);

    CreateCompleteFileResponseDTO objectResponse = wandleCompleteFileGIZuCompleteFileDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  private CreateCompleteFileResponseDTO wandleCompleteFileGIZuCompleteFileDTO(
      CreateCompleteFileGIResponse objectResponse) {
    return CreateCompleteFileResponseDTO.builder()
        .objidfile(objectResponse.getObjidfile())
        .objnamefile(objectResponse.getObjnamefile())
        .objidprocedure(objectResponse.getObjidprocedure())
        .objnameprocedure(objectResponse.getObjnameprocedure())
        .objiddocument(objectResponse.getObjiddocument())
        .objnamedocument(objectResponse.getObjnamedocument())
        .objidcontent(objectResponse.getObjidcontent())
        .objnamecontent(objectResponse.getObjnamecontent())
        .build();
  }
}
