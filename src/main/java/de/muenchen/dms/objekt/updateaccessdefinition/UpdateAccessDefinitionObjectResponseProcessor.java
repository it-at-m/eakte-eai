package de.muenchen.dms.objekt.updateaccessdefinition;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateAccessDefinitionObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateAccessDefinitionObjectResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    UpdateAccessDefinitionObjectGIResponse response =
        exchange.getIn().getBody(UpdateAccessDefinitionObjectGIResponse.class);
    final UpdateAccessDefinitionObjectAntwortDTO objectResponse =
        UpdateAccessDefinitionObjectAntwortDTO.builder()
            .objid(response.getObjid())
            .objname(response.getObjname())
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
