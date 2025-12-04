package de.muenchen.dms.userformsdata.read;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadUserFormsDataGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ReadUserFormsDataResponseProcessorTest {
  private static final int ERROR_CODE = 200;
  private static final String OVERVIEWADDRESS = "COO.1234.1234.1.1234";

  private ReadUserFormsDataResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadUserFormsDataResponseProcessor();
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

  private Exchange erzeugeExchangeObjekt(ReadUserFormsDataGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadUserFormsDataResponseDTO antwort =
        mapper.createParser(body).readValueAs(ReadUserFormsDataResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(ERROR_CODE));
    assertThat(antwort.getValue(), equalTo(OVERVIEWADDRESS));
  }

  private ReadUserFormsDataGIResponse erzeugeDmsAntwortOhneFehler() {
    ReadUserFormsDataGIResponse response = new ReadUserFormsDataGIResponse();
    response.setStatus(0);
    response.setErrormessage(null);
    response.setValue(OVERVIEWADDRESS);

    return response;
  }
}
