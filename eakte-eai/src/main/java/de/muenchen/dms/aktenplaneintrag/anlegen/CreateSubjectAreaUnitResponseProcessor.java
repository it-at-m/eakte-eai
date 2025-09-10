package de.muenchen.dms.aktenplaneintrag.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateSubjectAreaUnitGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CreateSubjectAreaUnitResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    CreateSubjectAreaUnitGIResponse response =
        exchange.getIn().getBody(CreateSubjectAreaUnitGIResponse.class);
    final CreateSubjectAreaUnitAntwortDTO objectResponse =
        CreateSubjectAreaUnitAntwortDTO.builder()
            .objid(response.getObjid())
            .objname(response.getObjname())
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
