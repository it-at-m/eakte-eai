package de.muenchen.dms.vorgang.aendern.complete.businessdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureBusinessDataGI;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import de.muenchen.dms.vorgang.anlegen.complete.businessdata.CreateCompleteProcedureBusinessDataBodyParams;
import de.muenchen.dms.vorgang.anlegen.complete.businessdata.CreateCompleteProcedureBusinessDataProcessor;
import jakarta.activation.DataHandler;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateCompleteProcedureBusinessDataProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~";
  private CreateCompleteProcedureBusinessDataProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateCompleteProcedureBusinessDataProcessor();
  }

  @Test
  void testeKonvertertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateCompleteProcedureBusinessDataBodyParams dto =
        CreateCompleteProcedureBusinessDataExample.erzeugeBeispielProcedureBusinessData();
    Exchange exchange = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(1));
    stelleDatentransferAufSOAPObjektSicher(dto, "anhang_0", exchange.getIn().getBody());
  }

  @Test
  void testeVorgangOhneSchriftstueck() {
    CreateCompleteProcedureBusinessDataBodyParams dto =
        CreateCompleteProcedureBusinessDataExample.erzeugeBeispielProcedureBusinessData();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  void testeVorgangMitSonderzeichen() throws Exception {
    CreateCompleteProcedureBusinessDataBodyParams dto =
        CreateCompleteProcedureBusinessDataExample.erzeugeBeispielProcedureBusinessData();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONDERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONDERZEICHEN, ""));
    Exchange exchange = prozessorAusfuehren(dto, anhaenge);
    stelleDatentransferAufSOAPObjektSicher(
        dto, DATEINAME_MIT_SONDERZEICHEN, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateCompleteProcedureBusinessDataBodyParams dto, Map<String, DataHandler> anhaenge)
      throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            ctx,
            dto,
            CreateCompleteProcedureBusinessDataBodyParams.class,
            CreateCompleteProcedureBusinessDataGI.class,
            anhaenge);
    processor.process(exchange);

    return exchange;
  }

  private void stelleDatentransferAufSOAPObjektSicher(
      CreateCompleteProcedureBusinessDataBodyParams dto,
      String DATEINAME_MIT_SONDERZEICHEN,
      Object message) {
    if (message instanceof CreateCompleteProcedureBusinessDataGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessterm(), equalTo(dto.getBusinessterm()));
      assertThat(gi.getBusinessvalue(), equalTo(dto.getBusinessvalue()));
      assertThat(gi.getShortnameprocedure(), equalTo(dto.getShortnameprocedure()));
      assertThat(gi.getShortnamedocument(), equalTo(dto.getShortnamedocument()));
      assertThat(
          gi.getGiattachmenttype().getLHMBAI151700Filename(), is(DATEINAME_MIT_SONDERZEICHEN));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
    } else {
      Assertions.fail();
    }
  }
}
