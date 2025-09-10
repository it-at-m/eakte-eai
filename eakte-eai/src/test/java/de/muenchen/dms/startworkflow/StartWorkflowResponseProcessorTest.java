package de.muenchen.dms.startworkflow;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.StartWorkflowGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class StartWorkflowResponseProcessorTest {

  private final int NO_ERROR_CODE = 0;
  private final String PROCESSID = "COO.1.2301.1.1042441";

  private StartWorkflowResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new StartWorkflowResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObject(erzeugeDMSAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObject(StartWorkflowGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private StartWorkflowGIResponse erzeugeDMSAntwortOhneFehler() {
    StartWorkflowGIResponse response = new StartWorkflowGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setProcessid(PROCESSID);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    StartWorkflowResponseDTO startWorkflowResponseDTO =
        mapper.createParser(body).readValueAs(StartWorkflowResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(startWorkflowResponseDTO.getProcessid(), equalTo(PROCESSID));
  }
}
