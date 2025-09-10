package de.muenchen.dms.aktenplaneintrag.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateSubjectAreaGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UpdateSubjectAreaResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {

    final UpdateSubjectAreaGIResponse response =
        exchange.getIn().getBody(UpdateSubjectAreaGIResponse.class);
    checkResponseNoContent(exchange, response.getStatus(), response.getErrormessage());
  }
}
