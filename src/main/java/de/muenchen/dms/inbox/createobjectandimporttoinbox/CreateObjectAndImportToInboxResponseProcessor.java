package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import static de.muenchen.dms.common.util.Umwandlungen.wandleArrayGIMetadataTypeZuMetadataReferenz;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateObjectAndImportToInboxGIResponse;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateObjectAndImportToInboxResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CreateObjectAndImportToInboxGIResponse response =
        exchange.getIn().getBody(CreateObjectAndImportToInboxGIResponse.class);
    final CreateObjectAndImportToInboxResponseDTO objectResponse =
        wandleCreateObjectAndImportToInboxGIZuDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), objectResponse);
  }

  public static CreateObjectAndImportToInboxResponseDTO wandleCreateObjectAndImportToInboxGIZuDTO(
      final CreateObjectAndImportToInboxGIResponse response) {
    return CreateObjectAndImportToInboxResponseDTO.builder()
        .listcontents(wandleArrayGIMetadataTypeZuMetadataReferenz(response.getListcontents()))
        .build();
  }
}
