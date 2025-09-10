package de.muenchen.dms.search.file;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchFileGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchFileResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    SearchFileGIResponse response = exchange.getIn().getBody(SearchFileGIResponse.class);

    final SearchFileResponseDTO objectResponse =
        SearchFileResponseDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
