package de.muenchen.dms.signature.set;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetSignatureObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SetSignatureResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    SetSignatureObjectGIResponse response =
        exchange.getIn().getBody(SetSignatureObjectGIResponse.class);

    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
