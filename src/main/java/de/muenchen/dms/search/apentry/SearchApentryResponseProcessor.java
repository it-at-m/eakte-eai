package de.muenchen.dms.search.apentry;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchApentryGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SearchApentryResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    SearchApentryGIResponse response = exchange.getIn().getBody(SearchApentryGIResponse.class);

    final SearchApentryResponseDTO objectResponse =
        SearchApentryResponseDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
