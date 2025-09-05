package de.muenchen.dms.inbox.createincomingfrominbox;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingFromInboxGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingFromInboxResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CreateIncomingFromInboxGIResponse response =
        exchange.getIn().getBody(CreateIncomingFromInboxGIResponse.class);

    handleResponse(
        exchange,
        response.getStatus(),
        response.getErrormessage(),
        response.getObjname(),
        response.getObjid());
  }
}
