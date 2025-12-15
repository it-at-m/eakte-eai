package de.muenchen.dms.schriftstueck.eingang.anlegen;

import static de.muenchen.dms.common.TestBeispielErzeuger.erstelleObjektId;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingGIResponse;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;
import java.util.stream.LongStream;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Die Testklasse prüft die Verarbeitung der SOAP-Antwort")
class CreateIncomingResponseProcessorTest {

  private static final int ERROR_CODE = 200;
  private static final String OBJECT_COOID = "COO.1234.1234.1.1234";
  private static final String OBJECT_NAME = "objektname";

  private static final String PREFIX_DOCUMENT = "Dokument";

  private CreateIncomingResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateIncomingResponseProcessor();
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

  private Exchange erzeugeExchangeObjekt(CreateIncomingGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateIncomingAntwortDTO antwort =
        mapper.createParser(body).readValueAs(CreateIncomingAntwortDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(ERROR_CODE));
    assertThat(antwort.getObjid(), equalTo(OBJECT_COOID));
    assertThat(antwort.getObjname(), equalTo(OBJECT_NAME));

    var erwarteteSchriftstuecke = erzeugeObjektReferenzen(this::erzeugeRestObjektreferenz);
    Assertions.assertEquals(erwarteteSchriftstuecke, antwort.getGiobjecttype());
  }

  private CreateIncomingGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateIncomingGIResponse response = new CreateIncomingGIResponse();
    response.setStatus(0);
    response.setErrormessage(null);
    response.setObjname(OBJECT_NAME);
    response.setObjid(OBJECT_COOID);

    ObjectFactory objectFactory = new ObjectFactory();
    var schriftstuecke = erzeugeObjektReferenzen(this::erzeugeSoapObjektreferenz);
    var schriftstueckContainer = objectFactory.createArrayOfLHMBAI151700GIObjectType();
    schriftstueckContainer.getLHMBAI151700GIObjectType().addAll(schriftstuecke);
    response.setGiobjecttype(schriftstueckContainer);

    return response;
  }

  private <T> List<T> erzeugeObjektReferenzen(
      BiFunction<String, String, T> objektReferenzFunktion) {
    return LongStream.rangeClosed(1, 3)
        .mapToObj(i -> objektReferenzFunktion.apply(erstelleObjektId(i), getDokumentName(i)))
        .toList();
  }

  private String getDokumentName(long id) {
    return String.format("%s %s", PREFIX_DOCUMENT, id);
  }

  private LHMBAI151700GIObjectType erzeugeSoapObjektreferenz(String id, String name) {
    var result = new LHMBAI151700GIObjectType();
    result.setLHMBAI151700Objaddress(id);
    result.setLHMBAI151700Objname(name);
    return result;
  }

  private Objektreferenz erzeugeRestObjektreferenz(String id, String name) {
    return Objektreferenz.builder().objaddress(id).objname(name).build();
  }
}
