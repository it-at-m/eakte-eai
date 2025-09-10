package de.muenchen.dms.schriftstueck.metadatenlesen;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielMetadatareferenz;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectMetaDataGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.MetadataReferenz;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ReadContentObjectMetaDataResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJECT_REFERRENDNUMBER = "objektname";

  private ReadContentObjectMetaDataResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadContentObjectMetaDataResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(ReadContentObjectMetaDataGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadContentObjectMetaDataAntwortDTO antwort =
        mapper.createParser(body).readValueAs(ReadContentObjectMetaDataAntwortDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getReferrednumber(), equalTo(OBJECT_REFERRENDNUMBER));
    Assertions.assertEquals(antwort.getGimetadatatype(), erzeugeBeispielMetadataReferenz());
  }

  private ReadContentObjectMetaDataGIResponse erzeugeDmsAntwortOhneFehler() {
    ReadContentObjectMetaDataGIResponse response = new ReadContentObjectMetaDataGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setReferrednumber(OBJECT_REFERRENDNUMBER);

    LHMBAI151700GIMetadataType metadataType =
        erzeugeBeispielMetadatareferenz(
            "COO.1.2301.1.1041875",
            "Antrag auf Baugenehmigung",
            "pdf",
            "PDF-Dokument",
            "243",
            null,
            null,
            null,
            null);

    response.setGimetadatatype(metadataType);

    return response;
  }

  private static MetadataReferenz erzeugeBeispielMetadataReferenz() {

    return MetadataReferenz.builder()
        .objaddress("COO.1.2301.1.1041875")
        .filename("Antrag auf Baugenehmigung")
        .fileextension("pdf")
        .objclass("PDF-Dokument")
        .contsize("243")
        .objcreatedby(null)
        .objcreatedat(null)
        .objchangedby(null)
        .objmodifiedat(null)
        .build();
  }
}
