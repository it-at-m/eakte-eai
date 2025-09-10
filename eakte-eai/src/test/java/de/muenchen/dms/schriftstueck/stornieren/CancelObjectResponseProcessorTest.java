package de.muenchen.dms.schriftstueck.stornieren;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CancelObjectGIResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CancelObjectResponseProcessorTest {
  private static final String ERROR_MESSAGE = "Fehler aufgetreten.";
  private static final int ERROR_CODE = 200;
  private static final int NO_ERROR_CODE = 0;

  private CancelObjectResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CancelObjectResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CancelObjectGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) {
    assertThat(
        exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.NO_CONTENT.value()));
  }

  private CancelObjectGIResponse erzeugeDmsAntwortOhneFehler() {
    CancelObjectGIResponse response = new CancelObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    return response;
  }

  private CancelObjectGIResponse erzeugeDmsAntwortMitFehler() {
    CancelObjectGIResponse response = new CancelObjectGIResponse();
    response.setStatus(ERROR_CODE);
    response.setErrormessage(ERROR_MESSAGE);
    return response;
  }
}
