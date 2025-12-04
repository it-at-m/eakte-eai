package de.muenchen.dms.userformsdata.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateUserFormsDataGIResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class UpdateUserFormsDataResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;

  private UpdateUserFormsDataResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateUserFormsDataResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(UpdateUserFormsDataGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) {
    assertThat(
        exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.NO_CONTENT.value()));
  }

  private UpdateUserFormsDataGIResponse erzeugeDmsAntwortOhneFehler() {
    UpdateUserFormsDataGIResponse response = new UpdateUserFormsDataGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    return response;
  }
}
