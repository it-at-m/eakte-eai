package de.muenchen.dms.vorgang.aendern.complete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import de.muenchen.dms.vorgang.anlegen.complete.CreateCompleteProcedureBodyParams;
import de.muenchen.dms.vorgang.anlegen.complete.CreateCompleteProcedureProcessor;
import jakarta.activation.DataHandler;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateCompleteProcedureProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~";
  static final String BEISPIEL_FILE_COOID = "COO.1";
  private CreateCompleteProcedureProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateCompleteProcedureProcessor();
  }

  @Test
  @DisplayName(
      """
                    Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
                    für einen SOAP-Call ohne Fehler umgewandelt werden kann.
                    """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateCompleteProcedureBodyParams dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(1));
    stelleDatentransferAufSoapObjektSicher(dto, "anhang_0", ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                        Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
                        """)
  void testVorgangsreferenzNull() {
    CreateCompleteProcedureBodyParams dto = erzeugeRestDatenobjekt();
    Map<String, DataHandler> anhaenge = null;
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, anhaenge));
  }

  @Test
  @DisplayName(
      "Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.")
  void testVorgangOhneSchriftstuecke() {
    CreateCompleteProcedureBodyParams dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  @DisplayName(
      """
                        Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
                        """)
  void testeSonderzeichen() throws Exception {
    CreateCompleteProcedureBodyParams dto = CreateCompleteProcedureBodyParams.sample();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONDERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONDERZEICHEN, ""));

    Exchange ex = prozessorAusfuehren(dto, anhaenge);
    stelleDatentransferAufSoapObjektSicher(dto, DATEINAME_MIT_SONDERZEICHEN, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateCompleteProcedureBodyParams dto, Map<String, DataHandler> anhaenge) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx,
            dto,
            CreateCompleteProcedureBodyParams.class,
            CreateCompleteProcedureGI.class,
            anhaenge);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_FILE_COOID);
    processor.process(ex);

    return ex;
  }

  private CreateCompleteProcedureBodyParams erzeugeRestDatenobjekt() {
    return CreateCompleteProcedureBodyParams.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateCompleteProcedureBodyParams dto, String DATEINAME_MIT_SONDERZEICHEN, Object message) {
    if (message instanceof CreateCompleteProcedureGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getFile(), equalTo(dto.getFile()));
      assertThat(gi.getShortnameprocedure(), equalTo(dto.getShortnameprocedure()));
      assertThat(gi.getShortnamedocument(), equalTo(dto.getShortnamedocument()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(
          gi.getGiattachmenttype().getLHMBAI151700Filename(), is(DATEINAME_MIT_SONDERZEICHEN));
    } else {
      Assertions.fail();
    }
  }
}
