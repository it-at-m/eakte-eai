package de.muenchen.dms.userformsdata.read;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadUserFormsDataGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadUserFormsDataResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    ReadUserFormsDataGIResponse response =
        exchange.getIn().getBody(ReadUserFormsDataGIResponse.class);

    ReadUserFormsDataResponseDTO objectResponse =
        wandleReadUserFormsDataGIToReadUserFormsDataResponseDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  private ReadUserFormsDataResponseDTO wandleReadUserFormsDataGIToReadUserFormsDataResponseDTO(
      ReadUserFormsDataGIResponse response) {
    return ReadUserFormsDataResponseDTO.builder().value(response.getValue()).build();
  }
}
