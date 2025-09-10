package de.muenchen.dms.schriftstueck.ausgang.lesen;

import static de.muenchen.dms.common.util.Umwandlungen.wandleZuOffsetDateTime;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadOutgoingGIResponse;
import de.muenchen.dms.common.model.MetadataReferenz;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ReadOutgoingResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final ReadOutgoingGIResponse response = exchange.getIn().getBody(ReadOutgoingGIResponse.class);
    final ReadOutgoingAntwortDTO objectResponse = wandleReadOutgoingGIZuAkteDTO(response);
    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static ReadOutgoingAntwortDTO wandleReadOutgoingGIZuAkteDTO(
      final ReadOutgoingGIResponse response) {
    return ReadOutgoingAntwortDTO.builderReadOutgoing()
        .objname(response.getObjname())
        .referrednumber(response.getReferrednumber())
        .shortname(response.getShortname())
        .referredincoming(response.getReferredincoming())
        .outgoingdate(wandleZuOffsetDateTime(response.getOutgoingdate()))
        .filesubj(response.getFilesubj())
        .subfiletype(response.getSubfiletype())
        .incattachments(response.getIncattachments())
        .searchalso(response.getSearchalso())
        .objterms(response.getObjterms())
        .gimetadatatype(wandleGiMetadataTypeZuMetadataReferenzen(response.getGimetadatatype()))
        .accdef(response.getAccdef())
        .businessapp(response.getBusinessapp())
        .build();
  }

  public static List<MetadataReferenz> wandleGiMetadataTypeZuMetadataReferenzen(
      ArrayOfLHMBAI151700GIMetadataType soapArray) {
    List<MetadataReferenz> metadatareferenzen = new ArrayList<>();

    if (soapArray != null && soapArray.getLHMBAI151700GIMetadataType() != null) {
      for (var objectType : soapArray.getLHMBAI151700GIMetadataType()) {
        metadatareferenzen.add(
            MetadataReferenz.builder()
                .objaddress(objectType.getLHMBAI151700Objaddress())
                .filename(objectType.getLHMBAI151700Filename())
                .fileextension(objectType.getLHMBAI151700Fileextension())
                .objclass(objectType.getLHMBAI151700Objclass())
                .contsize(objectType.getLHMBAI151700Contsize())
                .objcreatedby(objectType.getLHMBAI151700Objcreatedby())
                .objcreatedat(objectType.getLHMBAI151700Objcreatedat())
                .objchangedby(objectType.getLHMBAI151700Objchangedby())
                .objmodifiedat(objectType.getLHMBAI151700Objmodifiedat())
                .build());
      }
    }
    return metadatareferenzen;
  }
}
