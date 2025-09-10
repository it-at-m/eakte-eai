package de.muenchen.dms.akte.readapentry;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielListObjectTypeReferenz;
import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielObjectTypeReferenz;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadApentryGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ReadApentryResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;

  private ReadApentryResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadApentryResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws IOException {

    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehlermeldung());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(ReadApentryGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private ReadApentryGIResponse erzeugeDmsAntwortOhneFehlermeldung() {
    ReadApentryGIResponse response = new ReadApentryGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    ArrayOfLHMBAI151700GIObjectType listObjectType;
    listObjectType =
        erzeugeBeispielListObjectTypeReferenz(
            erzeugeBeispielObjectTypeReferenz("Testeingang", "COO.10.3040.3.2387"));
    response.setGiobjecttype(listObjectType);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadApentryAntwortDTO antwort =
        mapper.createParser(body).readValueAs(ReadApentryAntwortDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertEquals(antwort.getGiobjecttype(), erzeugeBeispielObjectReferenz());
  }

  private List<Objektreferenz> erzeugeBeispielObjectReferenz() {
    return Collections.singletonList(
        Objektreferenz.builder().objname("Testeingang").objaddress("COO.10.3040.3.2387").build());
  }
}
