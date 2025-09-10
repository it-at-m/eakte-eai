package de.muenchen.dms.akte.readsubjectareafile;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiObjectTypeZuObjektreferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadSubjectAreaFileGIObjectsResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadSubjectAreaFileResponseProcessor extends AbstractResponseProcessor {

  @Override
  public void process(final Exchange exchange) throws JsonProcessingException {
    final ReadSubjectAreaFileGIObjectsResponse response =
        exchange.getIn().getBody(ReadSubjectAreaFileGIObjectsResponse.class);
    final ReadSubjectAreaFileAntwortDTO objectResponse =
        wandleSubjectAreaFileGIZuSubjectAreaDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadSubjectAreaFileAntwortDTO wandleSubjectAreaFileGIZuSubjectAreaDTO(
      final ReadSubjectAreaFileGIObjectsResponse response) {
    return ReadSubjectAreaFileAntwortDTO.builderReadSubjectAreaFile()
        .giobjecttype(wandleGiObjectTypeZuObjektreferenzen(response.getGiobjecttype()))
        .build();
  }
}
