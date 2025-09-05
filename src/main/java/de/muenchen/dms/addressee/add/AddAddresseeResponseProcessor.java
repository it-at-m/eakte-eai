package de.muenchen.dms.addressee.add;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.AddAddresseeResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AddAddresseeResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    AddAddresseeResponse response = exchange.getIn().getBody(AddAddresseeResponse.class);

    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
