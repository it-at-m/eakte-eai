/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.aendern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateProcedureGIResponse;
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

@DisplayName("[Vorgang aendern] Die Testklasse prüft die Verarbeitung der SOAP-Antwort")
class UpdateProcedureResponseProcessorTest {
  private static final String ERROR_MESSAGE = "Fehler aufgetreten.";
  private static final int ERROR_CODE = 200;
  private static final int NO_ERROR_CODE = 0;
  private static final String OBJECT_COOID = "COO.1234.1234.1.1234";
  private static final String OBJECT_NAME = "objektname";

  private UpdateProcedureResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateProcedureResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(UpdateProcedureGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    DmsObjektResponse dmsObjektResponse =
        mapper.createParser(body).readValueAs(DmsObjektResponse.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(dmsObjektResponse.getObjid(), equalTo(OBJECT_COOID));
    assertThat(dmsObjektResponse.getObjname(), equalTo(OBJECT_NAME));
  }

  private UpdateProcedureGIResponse erzeugeDmsAntwortOhneFehler() {
    UpdateProcedureGIResponse response = new UpdateProcedureGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjname(OBJECT_NAME);
    response.setObjid(OBJECT_COOID);
    return response;
  }

  private UpdateProcedureGIResponse erzeugeDmsAntwortMitFehler() {
    UpdateProcedureGIResponse response = new UpdateProcedureGIResponse();
    response.setStatus(ERROR_CODE);
    response.setErrormessage(ERROR_MESSAGE);
    response.setObjname(null);
    response.setObjid(null);
    return response;
  }
}
