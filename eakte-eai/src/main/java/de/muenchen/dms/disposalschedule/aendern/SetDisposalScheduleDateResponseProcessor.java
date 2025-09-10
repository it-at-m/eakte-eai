package de.muenchen.dms.disposalschedule.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetDisposalScheduleDateGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SetDisposalScheduleDateResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(final Exchange exchange) throws Exception {
    SetDisposalScheduleDateGIResponse response =
        exchange.getIn().getBody(SetDisposalScheduleDateGIResponse.class);
    final SetDisposalScheduleDateAntwortDTO objectResponse =
        SetDisposalScheduleDateAntwortDTO.builder()
            .objid(response.getObjid())
            .objname(response.getObjname())
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
