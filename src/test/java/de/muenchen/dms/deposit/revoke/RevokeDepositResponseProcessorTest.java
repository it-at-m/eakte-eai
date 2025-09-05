package de.muenchen.dms.deposit.revoke;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.RevokeDepositObjectGIResponse;
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

public class RevokeDepositResponseProcessorTest {
  private static final String ERROR_MESSAGE = "Fehler aufgetreten";
  private static final int ERROR_CODE = 2;
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJADDRESS = "COO.1234.5678.90";
  private static final String OBJNAME = "objname";

  private RevokeDepositResponseProcessor revokeDepositResponseProcessor;

  @BeforeEach
  public void setUp() {
    revokeDepositResponseProcessor = new RevokeDepositResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung des DMS ohne Fehlermeldung")
  void testeErgebnisohneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObject(erzeugeDMSAnwortOhneFehler());

    revokeDepositResponseProcessor.process(exchange);

    pruefeDMSAntwort(exchange);
  }

  private Exchange erzeugeExchangeObject(RevokeDepositObjectGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeDMSAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    DmsObjektResponse dmsObjektResponse =
        mapper.createParser(body).readValueAs(DmsObjektResponse.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(dmsObjektResponse.getObjid(), equalTo(OBJADDRESS));
    assertThat(dmsObjektResponse.getObjname(), equalTo(OBJNAME));
  }

  public RevokeDepositObjectGIResponse erzeugeDMSAnwortOhneFehler() {
    RevokeDepositObjectGIResponse response = new RevokeDepositObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setObjname(OBJNAME);
    response.setErrormessage(null);
    response.setObjid(OBJADDRESS);

    return response;
  }

  public RevokeDepositObjectGIResponse erzeugeDMSAnwortMitFehler() {
    RevokeDepositObjectGIResponse response = new RevokeDepositObjectGIResponse();
    response.setStatus(ERROR_CODE);
    response.setObjname(null);
    response.setErrormessage(ERROR_MESSAGE);
    response.setObjid(null);
    return response;
  }
}
