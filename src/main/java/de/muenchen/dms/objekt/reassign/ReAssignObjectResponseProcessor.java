package de.muenchen.dms.objekt.reassign;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReAssignObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReAssignObjectResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final ReAssignObjectGIResponse response =
        exchange.getIn().getBody(ReAssignObjectGIResponse.class);
    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
