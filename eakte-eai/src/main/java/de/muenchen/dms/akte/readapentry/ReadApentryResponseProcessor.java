package de.muenchen.dms.akte.readapentry;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadApentryGIResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadApentryResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final ReadApentryGIResponse response = exchange.getIn().getBody(ReadApentryGIResponse.class);
    final ReadApentryAntwortDTO objectResponse = wandleSubjectAreaFileGIZuSubjectAreaDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadApentryAntwortDTO wandleSubjectAreaFileGIZuSubjectAreaDTO(
      final ReadApentryGIResponse response) {
    return ReadApentryAntwortDTO.builder()
        .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
        .build();
  }
}
