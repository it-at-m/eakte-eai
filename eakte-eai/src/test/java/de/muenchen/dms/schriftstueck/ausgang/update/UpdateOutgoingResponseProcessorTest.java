package de.muenchen.dms.schriftstueck.ausgang.update;

import static de.muenchen.dms.common.TestBeispielErzeuger.erstelleObjektId;
import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielObjektreferenzen;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateOutgoingGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

@DisplayName("[Ausgangsdokument anlegen] Die Testklasse prüft die Verarbeitung der SOAP-Antwort")
class UpdateOutgoingResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;
  private static final String OBJECT_COOID = "COO.1234.1234.1.1234";
  private static final String OBJECT_NAME = "objektname";

  private UpdateOutgoingResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new UpdateOutgoingResponseProcessor();
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

  private Exchange erzeugeExchangeObjekt(UpdateOutgoingGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    UpdateOutgoingResponseDTO antwort =
        mapper.createParser(body).readValueAs(UpdateOutgoingResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getObjid(), equalTo(OBJECT_COOID));
    assertThat(antwort.getObjname(), equalTo(OBJECT_NAME));

    Assertions.assertEquals(antwort.getGiobjecttype(), erzeugeErwarteteObjektReferenzen());
  }

  private UpdateOutgoingGIResponse erzeugeDmsAntwortOhneFehler() {

    UpdateOutgoingGIResponse response = new UpdateOutgoingGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjname(OBJECT_NAME);
    response.setObjid(OBJECT_COOID);
    response.setGiobjecttype(erzeugeBeispielObjektreferenzen(1, 4, "Dokument", true));
    return response;
  }

  private List<Objektreferenz> erzeugeErwarteteObjektReferenzen() {
    List<Objektreferenz> result = new ArrayList<>();
    result.add(
        Objektreferenz.builder().objaddress(erstelleObjektId(1)).objname("Dokument 1").build());
    result.add(
        Objektreferenz.builder().objaddress(erstelleObjektId(2)).objname("Dokument 2").build());
    result.add(
        Objektreferenz.builder().objaddress(erstelleObjektId(3)).objname("Dokument 3").build());
    return result;
  }
}
