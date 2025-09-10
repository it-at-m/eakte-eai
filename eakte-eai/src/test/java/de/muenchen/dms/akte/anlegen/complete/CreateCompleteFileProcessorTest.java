package de.muenchen.dms.akte.anlegen.complete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteFileGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import jakarta.activation.DataHandler;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateCompleteFileProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~";
  static final String BEISPIEL_APENTRY_COOID = "COO.1.1.1";

  private CreateCompleteFileProcessor createCompleteFileProcessor;

  @BeforeEach
  void setUp() {
    createCompleteFileProcessor = new CreateCompleteFileProcessor();
  }

  @Test
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateCompleteFileBodyParams dto = erzeugeRestDatenobjekt();
    Exchange exchange = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(1));
    stelleDatentransferAufSOAPObjektSicher(dto, "anhang_0", exchange.getIn().getBody());
  }

  @Test
  void testFileOhneSchriftstueck() {
    CreateCompleteFileBodyParams dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  void testeSonderzeichen() throws Exception {
    CreateCompleteFileBodyParams dto = CreateCompleteFileBodyParams.sample();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONDERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONDERZEICHEN, ""));

    Exchange exchange = prozessorAusfuehren(dto, anhaenge);

    stelleDatentransferAufSOAPObjektSicher(
        dto, DATEINAME_MIT_SONDERZEICHEN, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateCompleteFileBodyParams dto, Map<String, DataHandler> anhaenge) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            ctx, dto, CreateCompleteFileBodyParams.class, CreateCompleteFileGI.class, anhaenge);
    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_APENTRY_COOID);
    createCompleteFileProcessor.process(exchange);
    return exchange;
  }

  private CreateCompleteFileBodyParams erzeugeRestDatenobjekt() {
    return CreateCompleteFileBodyParams.sample();
  }

  private void stelleDatentransferAufSOAPObjektSicher(
      CreateCompleteFileBodyParams dto, String DATEINAME_MIT_SONDERZEICHEN, Object message) {
    if (message instanceof CreateCompleteFileGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getApentry(), equalTo(dto.getApentry()));
      assertThat(gi.getShortnamefile(), equalTo(dto.getShortnamefile()));
      assertThat(gi.getShortnameprocedure(), equalTo(dto.getShortnameprocedure()));
      assertThat(gi.getShortnamedocument(), equalTo(dto.getShortnamedocument()));
      assertThat(gi.getFileouobj(), equalTo(dto.getFileouobj()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(
          gi.getGiattachmenttype().getLHMBAI151700Filename(), is(DATEINAME_MIT_SONDERZEICHEN));
    } else {
      Assertions.fail();
    }
  }
}
