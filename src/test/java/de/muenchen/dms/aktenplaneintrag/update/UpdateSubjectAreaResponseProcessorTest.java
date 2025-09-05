/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2025
 */

package de.muenchen.dms.aktenplaneintrag.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateSubjectAreaGIResponse;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("[Aktenplaneintragtyp ändern] Die Testklasse prüft die Verarbeitung der SOAP-Antwort")
public class UpdateSubjectAreaResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;
  private UpdateSubjectAreaResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateSubjectAreaResponseProcessor();
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

  private Exchange erzeugeExchangeObjekt(UpdateSubjectAreaGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    assertThat(
        exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.NO_CONTENT.value()));
  }

  private UpdateSubjectAreaGIResponse erzeugeDmsAntwortOhneFehler() {
    UpdateSubjectAreaGIResponse response = new UpdateSubjectAreaGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);

    return response;
  }
}
