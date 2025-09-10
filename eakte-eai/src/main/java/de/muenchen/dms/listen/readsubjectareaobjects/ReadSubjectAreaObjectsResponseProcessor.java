package de.muenchen.dms.listen.readsubjectareaobjects;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadSubjectAreaGIObjectsResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadSubjectAreaObjectsResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    ReadSubjectAreaGIObjectsResponse response =
        exchange.getIn().getBody(ReadSubjectAreaGIObjectsResponse.class);
    final ReadSubjectAreaObjectsAntwortDTO objectResponse =
        ReadSubjectAreaObjectsAntwortDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
