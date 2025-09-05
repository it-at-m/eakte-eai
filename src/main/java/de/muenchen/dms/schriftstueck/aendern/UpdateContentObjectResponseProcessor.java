package de.muenchen.dms.schriftstueck.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateContentObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateContentObjectResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    final UpdateContentObjectGIResponse response =
        exchange.getIn().getBody(UpdateContentObjectGIResponse.class);

    final UpdateContentObjectAntwortDTO objectResponse =
        UpdateContentObjectAntwortDTO.builder()
            .contentobjid(response.getContentobjid())
            .contentobjname(response.getContentobjname())
            .objid(response.getObjid())
            .objname(response.getObjname())
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
