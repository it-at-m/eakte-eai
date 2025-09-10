package de.muenchen.dms.objekt.reassign;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReAssignObjectGIResponse;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ReAssignObjectResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;

  private ReAssignObjectResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new ReAssignObjectResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObject(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObject(ReAssignObjectGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) {
    assertThat(
        exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.NO_CONTENT.value()));
  }

  private ReAssignObjectGIResponse erzeugeDmsAntwortOhneFehler() {
    ReAssignObjectGIResponse response = new ReAssignObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    return response;
  }
}
