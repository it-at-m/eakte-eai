package de.muenchen.dms.schriftstueck.internal.create;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateInternalGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import de.muenchen.dms.common.util.Umwandlungen;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateInternalResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    CreateInternalGIResponse response = exchange.getIn().getBody(CreateInternalGIResponse.class);

    final CreateInternalResponseDTO objectResponse =
        CreateInternalResponseDTO.builder()
            .giobjecttype(
                Umwandlungen.wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    objectResponse.setObjid(response.getObjid());
    objectResponse.setObjname(response.getObjname());
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
