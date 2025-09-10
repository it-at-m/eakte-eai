package de.muenchen.dms.schriftstueck.ausgang.update;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateOutgoingGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UpdateOutgoingResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(Exchange exchange) throws Exception {
    UpdateOutgoingGIResponse response = exchange.getIn().getBody(UpdateOutgoingGIResponse.class);

    final UpdateOutgoingResponseDTO objectResponse =
        UpdateOutgoingResponseDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    objectResponse.setObjname(response.getObjname());
    objectResponse.setObjid(response.getObjid());
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
