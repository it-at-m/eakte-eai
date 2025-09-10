package de.muenchen.dms.deposit.revoke;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.RevokeDepositObjectGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class RevokeDepositResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final RevokeDepositObjectGIResponse response =
        exchange.getIn().getBody(RevokeDepositObjectGIResponse.class);
    handleResponse(
        exchange,
        response.getStatus(),
        response.getErrormessage(),
        response.getObjname(),
        response.getObjid());
  }
}
