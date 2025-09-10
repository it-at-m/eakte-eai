package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielListMetadatareferenz;
import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielMetadatareferenz;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateObjectAndImportToInboxGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.MetadataReferenz;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CreateObjectAndImportToInboxResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;

  private CreateObjectAndImportToInboxResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateObjectAndImportToInboxResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateObjectAndImportToInboxGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateObjectAndImportToInboxResponseDTO antwort =
        mapper.createParser(body).readValueAs(CreateObjectAndImportToInboxResponseDTO.class);
    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    Assertions.assertEquals(antwort.getListcontents(), erzeugeBeispielAttachmentReferenz());
  }

  private CreateObjectAndImportToInboxGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateObjectAndImportToInboxGIResponse response = new CreateObjectAndImportToInboxGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    ArrayOfLHMBAI151700GIMetadataType listAttachmentType;
    listAttachmentType =
        erzeugeBeispielListMetadatareferenz(
            erzeugeBeispielMetadatareferenz(
                "COO.1.2301.1.1041875",
                "Antrag auf Baugenehmigung",
                "pdf",
                "PDF-Dokument",
                "243",
                null,
                null,
                null,
                null));
    response.setListcontents(listAttachmentType);
    return response;
  }

  private static List<MetadataReferenz> erzeugeBeispielAttachmentReferenz() {

    return Collections.singletonList(
        MetadataReferenz.builder()
            .objaddress("COO.1.2301.1.1041875")
            .filename("Antrag auf Baugenehmigung")
            .fileextension("pdf")
            .objclass("PDF-Dokument")
            .contsize("243")
            .objcreatedby(null)
            .objcreatedat(null)
            .objchangedby(null)
            .objmodifiedat(null)
            .build());
  }
}
