package de.muenchen.dms.listen.readprocedureobjects;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIObjectsResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadProcedureObjectsResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    ReadProcedureGIObjectsResponse response =
        exchange.getIn().getBody(ReadProcedureGIObjectsResponse.class);
    final ReadProcedureObjectsAntwortDTO objectResponse =
        ReadProcedureObjectsAntwortDTO.builder()
            .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
