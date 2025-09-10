package de.muenchen.dms.search.apentry;

import static de.muenchen.dms.common.TestBeispielErzeuger.erstelleObjektId;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchApentryGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
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

class SearchApentryResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;

  private static final String PREFIX_DOCUMENT = "document";

  private SearchApentryResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new SearchApentryResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisoheFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    SearchApentryResponseDTO antwort =
        mapper.createParser(body).readValueAs(SearchApentryResponseDTO.class);

    var erwarteteSchriftstuecke = erzeugeObjektReferenzen(this::erzeugeRestObjektreferenz);
    Assertions.assertEquals(erwarteteSchriftstuecke, antwort.getGiobjecttype());
  }

  private Exchange erzeugeExchangeObjekt(SearchApentryGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private SearchApentryGIResponse erzeugeDmsAntwortOhneFehler() {
    ObjectFactory objectFactory = new ObjectFactory();

    SearchApentryGIResponse response = new SearchApentryGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    var giObject = erzeugeObjektReferenzen(this::erzeugeSoapObjektreferenz);
    var giObjectContainer = objectFactory.createArrayOfLHMBAI151700GIObjectType();
    giObjectContainer.getLHMBAI151700GIObjectType().addAll(giObject);
    response.setGiobjecttype(giObjectContainer);
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
