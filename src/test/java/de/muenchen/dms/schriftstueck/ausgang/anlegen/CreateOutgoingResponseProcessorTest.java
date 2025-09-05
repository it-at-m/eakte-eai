package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielObjektreferenzen;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateOutgoingGIResponse;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("[Ausgangsdokument anlegen] Die Testklasse prüft die Verarbeitung der SOAP-Antwort")
class CreateOutgoingResponseProcessorTest {

  private static final String ERROR_MESSAGE = "Fehler aufgetreten.";
  private static final int ERROR_CODE = 200;
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJECT_COOID = "COO.1234.1234.1.1234";
  private static final String OBJECT_NAME = "objektname";

  private CreateOutgoingResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateOutgoingResponseProcessor();
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

  private Exchange erzeugeExchangeObjekt(CreateOutgoingGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateOutgoingAntwortDTO antwort =
        mapper.createParser(body).readValueAs(CreateOutgoingAntwortDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getObjid(), equalTo(OBJECT_COOID));
    assertThat(antwort.getObjname(), equalTo(OBJECT_NAME));
  }

  private CreateOutgoingGIResponse erzeugeDmsAntwortOhneFehler() {
    ObjectFactory objectFactory = new ObjectFactory();

    CreateOutgoingGIResponse response = new CreateOutgoingGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjname(OBJECT_NAME);
    response.setObjid(OBJECT_COOID);
    response.setGiobjecttype(erzeugeBeispielObjektreferenzen(1, 4, "Dokument", true));
    return response;
  }

  private CreateOutgoingGIResponse erzeugeDmsAntwortMitFehler() {
    CreateOutgoingGIResponse response = new CreateOutgoingGIResponse();
    response.setStatus(ERROR_CODE);
    response.setErrormessage(ERROR_MESSAGE);
    response.setObjname(null);
    response.setObjid(null);
    response.setGiobjecttype(null);
    return response;
  }
}
