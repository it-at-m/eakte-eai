package de.muenchen.dms.search.procedure;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchProcedureGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchProcedureResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    SearchProcedureGIResponse response = exchange.getIn().getBody(SearchProcedureGIResponse.class);

    final SearchProcedureResponseDTO objectResponse =
        SearchProcedureResponseDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
