package de.muenchen.dms.deposit;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.DepositObjectGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class DepositResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(Exchange exchange) throws Exception {
    final DepositObjectGIResponse response =
        exchange.getIn().getBody(DepositObjectGIResponse.class);
    handleResponse(
        exchange,
        response.getStatus(),
        response.getErrormessage(),
        response.getObjname(),
        response.getObjid());
  }
}
