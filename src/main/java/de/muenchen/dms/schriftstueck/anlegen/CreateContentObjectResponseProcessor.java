package de.muenchen.dms.schriftstueck.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateContentObjectGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateContentObjectResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final CreateContentObjectGIResponse response =
        exchange.getIn().getBody(CreateContentObjectGIResponse.class);

    final CreateContentObjectAntwortDTO objectResponse =
        wandleCreateContentObjectGIZuCreateContentObjectDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static CreateContentObjectAntwortDTO wandleCreateContentObjectGIZuCreateContentObjectDTO(
      final CreateContentObjectGIResponse response) {
    return CreateContentObjectAntwortDTO.builder().objid(response.getObjid()).build();
  }
}
