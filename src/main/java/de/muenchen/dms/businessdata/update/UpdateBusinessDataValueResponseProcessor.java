package de.muenchen.dms.businessdata.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateBusinessDataValueGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateBusinessDataValueResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final UpdateBusinessDataValueGIResponse response =
        exchange.getIn().getBody(UpdateBusinessDataValueGIResponse.class);
    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
