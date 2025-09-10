package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureFromTemplateGIResponse;
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

class CreateProcedureFromTemplateResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJID = "COO.1.2301.1.1042432";
  private static final String OBJNAME = "2016 Anträge Firma XY";

  private CreateProcedureFromTemplateResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateProcedureFromTemplateResponseProcessor();
  }

  @Test
  void testErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDMSAnwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateProcedureFromTemplateGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private CreateProcedureFromTemplateGIResponse erzeugeDMSAnwortOhneFehler() {
    CreateProcedureFromTemplateGIResponse response = new CreateProcedureFromTemplateGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjid(OBJID);
    response.setObjname(OBJNAME);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    DmsObjektResponse response = mapper.createParser(body).readValueAs(DmsObjektResponse.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(response.getObjid(), equalTo(OBJID));
    assertThat(response.getObjname(), equalTo(OBJNAME));
  }
}
