package de.muenchen.dms.deposit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.DepositObjectGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.DmsObjektResponse;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class DepositResponseProcessorTest {
  private static final String ERROR_MESSAGE = "fehler aufgetreten";
  private static final int ERROR_CODE = 2;
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJADDRESS = "COO.1234.5678.90";
  private static final String OBJNAME = "objname";

  private DepositResponseProcessor depositResponseProcessor;

  @BeforeEach
  public void setUp() {
    depositResponseProcessor = new DepositResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObject(erzeugeDMSAnwortOhneFehler());

    depositResponseProcessor.process(exchange);

    pruefeDmsAntwort(exchange);
  }

  private Exchange erzeugeExchangeObject(DepositObjectGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeDmsAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper objectMapper = new ObjectMapper();
    DmsObjektResponse dmsObjektResponse =
        objectMapper.createParser(body).readValueAs(DmsObjektResponse.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(dmsObjektResponse.getObjid(), equalTo(OBJADDRESS));
    assertThat(dmsObjektResponse.getObjname(), equalTo(OBJNAME));
  }

  private DepositObjectGIResponse erzeugeDMSAnwortOhneFehler() {
    DepositObjectGIResponse response = new DepositObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjid(OBJADDRESS);
    response.setObjname(OBJNAME);

    return response;
  }

  private DepositObjectGIResponse erzeugeDmsAntwortMitFehler() {
    DepositObjectGIResponse response = new DepositObjectGIResponse();
    response.setStatus(ERROR_CODE);
    response.setObjid(null);
    response.setErrormessage(ERROR_MESSAGE);
    response.setObjname(null);

    return response;
  }
}
