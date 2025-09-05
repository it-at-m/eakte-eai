package de.muenchen.dms.inbox.createincomingfrominbox;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingFromInboxGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.DmsObjektResponse;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CreateIncomingFromInboxResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;
  private static final String OBJID = "COO.1.2301.1.1042441";
  private static final String OBJNAME = "Eingangspost vom 21.10.2016";

  private CreateIncomingFromInboxResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateIncomingFromInboxResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  public Exchange erzeugeExchangeObjekt(CreateIncomingFromInboxGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  public CreateIncomingFromInboxGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateIncomingFromInboxGIResponse response = new CreateIncomingFromInboxGIResponse();
    response.setErrormessage(null);
    response.setStatus(NO_ERROR_CODE);
    response.setObjid(OBJID);
    response.setObjname(OBJNAME);
    return response;
  }

  public void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    DmsObjektResponse responseDTO = mapper.createParser(body).readValueAs(DmsObjektResponse.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(responseDTO.getObjid(), equalTo(OBJID));
    assertThat(responseDTO.getObjname(), equalTo(OBJNAME));
  }
}
