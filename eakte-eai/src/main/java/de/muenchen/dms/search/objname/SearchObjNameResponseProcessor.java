package de.muenchen.dms.search.objname;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchObjNameGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SearchObjNameResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    SearchObjNameGIResponse response = exchange.getIn().getBody(SearchObjNameGIResponse.class);
    final SearchObjNameAntwortDTO objectResponse =
        SearchObjNameAntwortDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
