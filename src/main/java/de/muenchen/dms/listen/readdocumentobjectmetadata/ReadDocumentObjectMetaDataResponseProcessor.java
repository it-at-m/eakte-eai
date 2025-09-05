package de.muenchen.dms.listen.readdocumentobjectmetadata;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadDocumentGIObjectsMetaDataResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import de.muenchen.dms.common.util.Umwandlungen;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadDocumentObjectMetaDataResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    ReadDocumentGIObjectsMetaDataResponse response =
        exchange.getIn().getBody(ReadDocumentGIObjectsMetaDataResponse.class);

    final ReadDocumentObjectMetaDataResponseDTO objectResponse =
        ReadDocumentObjectMetaDataResponseDTO.builder()
            .businessobjecttype(
                Umwandlungen.wandleBusinessObjectTypeZuBusinessObjectReference(
                    response.getBusinessobjecttype()))
            .build();
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }
}
