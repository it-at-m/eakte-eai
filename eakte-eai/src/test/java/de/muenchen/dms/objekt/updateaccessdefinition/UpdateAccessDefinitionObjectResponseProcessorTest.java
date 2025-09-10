package de.muenchen.dms.objekt.updateaccessdefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateAccessDefinitionObjectGIResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class UpdateAccessDefinitionObjectResponseProcessorTest {
  private static final String ERROR_MESSAGE = "Fehler aufgetreten.";
  private static final int ERROR_CODE = 204;
  private static final int NO_ERROR_CODE = 0;

  private UpdateAccessDefinitionObjectResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateAccessDefinitionObjectResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(UpdateAccessDefinitionObjectGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) {
    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
  }

  private UpdateAccessDefinitionObjectGIResponse erzeugeDmsAntwortOhneFehler() {
    UpdateAccessDefinitionObjectGIResponse response = new UpdateAccessDefinitionObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    return response;
  }

  private UpdateAccessDefinitionObjectGIResponse erzeugeDmsAntwortMitFehler() {
    UpdateAccessDefinitionObjectGIResponse response = new UpdateAccessDefinitionObjectGIResponse();
    response.setStatus(ERROR_CODE);
    response.setErrormessage(ERROR_MESSAGE);
    return response;
  }
}
