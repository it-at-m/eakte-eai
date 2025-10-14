package de.muenchen.dms.userformsdata.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateUserFormsDataGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFormsDataResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final UpdateUserFormsDataGIResponse response =
        exchange.getIn().getBody(UpdateUserFormsDataGIResponse.class);

    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
