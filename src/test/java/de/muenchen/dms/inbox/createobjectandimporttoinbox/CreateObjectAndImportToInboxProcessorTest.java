package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateObjectAndImportToInboxGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import jakarta.activation.DataHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateObjectAndImportToInboxProcessorTest {
  static final String DATEINAME_MIT_SONERZEICHEN = "!@#$%^&*()_+|}{[],><?-~";
  static final String BEISPIEL_OBJADDRESS_POSTKORB = "COO.1";

  private CreateObjectAndImportToInboxProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateObjectAndImportToInboxProcessor();
  }

  @Test
  @DisplayName(
      """
              Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
              für einen SOAP-Call ohne Fehler umgewandelt werden kann.
              """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateObjectAndImportToInboxDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2));
    stelleDatentransferAufSoapObjektSicher(dto, 2, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
                  ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    CreateObjectAndImportToInboxDTO dto = new CreateObjectAndImportToInboxDTO();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap());
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testVorgangsreferenzNull() {
    CreateObjectAndImportToInboxDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2)));
  }

  @Test
  @DisplayName(
      "Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.")
  void testVorgangOhneSchriftstuecke() {
    CreateObjectAndImportToInboxDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  @DisplayName(
      """
                  Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
                  """)
  void testeSonderzeichen() throws Exception {
    CreateObjectAndImportToInboxDTO dto = CreateObjectAndImportToInboxDTO.sample();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONERZEICHEN, ""));

    Exchange ex = prozessorAusfuehren(dto, anhaenge);
    stelleDatentransferAufSoapObjektSicher(dto, 1, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateObjectAndImportToInboxDTO dto, Map<String, DataHandler> anhaenge) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, CreateObjectAndImportToInboxDTO.class, CreateIncomingGI.class, anhaenge);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_OBJADDRESS_POSTKORB);
    processor.process(ex);

    return ex;
  }

  private CreateObjectAndImportToInboxDTO erzeugeRestDatenobjekt() {
    return CreateObjectAndImportToInboxDTO.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateObjectAndImportToInboxDTO dto, int anzahlAnhaenge, Object message) {
    if (message instanceof CreateObjectAndImportToInboxGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getObjaddress(), equalTo(dto.getObjaddress()));

      if (anzahlAnhaenge == 0) {
        assertThat(gi.getGiattachmenttype(), nullValue());
      } else {
        assertThat(
            gi.getGiattachmenttype().getLHMBAI151700GIAttachmentType().size(), is(anzahlAnhaenge));
      }

    } else {
      Assertions.fail();
    }
  }
}
