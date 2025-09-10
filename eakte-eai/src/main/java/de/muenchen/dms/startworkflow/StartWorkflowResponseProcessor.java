package de.muenchen.dms.startworkflow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.StartWorkflowGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class StartWorkflowResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    StartWorkflowGIResponse response = exchange.getIn().getBody(StartWorkflowGIResponse.class);

    StartWorkflowResponseDTO objectResponse = wandleStartWorkflowGIZuStartWorkflowDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static StartWorkflowResponseDTO wandleStartWorkflowGIZuStartWorkflowDTO(
      StartWorkflowGIResponse objectResponse) {
    return StartWorkflowResponseDTO.builder().processid(objectResponse.getProcessid()).build();
  }
}
