package de.muenchen.dms.schriftstueck.stornieren;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CancelObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CancelObjectResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CancelObjectGIResponse response = exchange.getIn().getBody(CancelObjectGIResponse.class);
    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
