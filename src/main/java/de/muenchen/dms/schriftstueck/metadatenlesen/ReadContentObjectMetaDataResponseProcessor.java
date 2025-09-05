package de.muenchen.dms.schriftstueck.metadatenlesen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleGiMetadataTypeZuMetadataReferenzen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectMetaDataGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadContentObjectMetaDataResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final ReadContentObjectMetaDataGIResponse response =
        exchange.getIn().getBody(ReadContentObjectMetaDataGIResponse.class);
    final ReadContentObjectMetaDataAntwortDTO objectResponse =
        wandleReadOutgoingGIZuAkteDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadContentObjectMetaDataAntwortDTO wandleReadOutgoingGIZuAkteDTO(
      final ReadContentObjectMetaDataGIResponse response) {
    return ReadContentObjectMetaDataAntwortDTO.builder()
        .referrednumber(response.getReferrednumber())
        .gimetadatatype(wandleGiMetadataTypeZuMetadataReferenzen(response.getGimetadatatype()))
        .build();
  }
}
