package de.muenchen.dms.aktenplaneintrag.anlegen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateSubjectAreaUnitGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateSubjectAreaUnitResponseProcessorTest {

  private static final String ERROR_MESSAGE = "Fehler aufgetreten.";
  private static final int ERROR_CODE = 200;
  private static final String OBJECT_COOID = "COO.1234.1234.1.1234";
  private static final String OBJECT_NAME = "objektname";

  private CreateSubjectAreaUnitResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateSubjectAreaUnitResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verabeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateSubjectAreaUnitGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateSubjectAreaUnitAntwortDTO antwort =
        mapper.createParser(body).readValueAs(CreateSubjectAreaUnitAntwortDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(ERROR_CODE));
    assertThat(antwort.getObjid(), equalTo(OBJECT_COOID));
    assertThat(antwort.getObjname(), equalTo(OBJECT_NAME));
  }

  private CreateSubjectAreaUnitGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateSubjectAreaUnitGIResponse response = new CreateSubjectAreaUnitGIResponse();
    response.setStatus(0);
    response.setErrormessage(null);
    response.setObjname(OBJECT_NAME);
    response.setObjid(OBJECT_COOID);

    return response;
  }

  private CreateSubjectAreaUnitGIResponse erzeugeDmsAntwortMitFehler() {
    CreateSubjectAreaUnitGIResponse response = new CreateSubjectAreaUnitGIResponse();
    response.setStatus(200);
    response.setErrormessage(ERROR_MESSAGE);
    response.setBusinessapp(null);
    response.setObjid(null);
    return response;
  }
}
