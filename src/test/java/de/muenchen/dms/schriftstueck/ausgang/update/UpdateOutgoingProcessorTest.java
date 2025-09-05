package de.muenchen.dms.schriftstueck.ausgang.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateOutgoingGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateOutgoingGI;
import de.muenchen.dms.common.model.DMSContainerBase;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import jakarta.activation.DataHandler;
import java.util.Collections;
import java.util.Map;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Die Testklasse prüft die Verarbeitung von REST-Anfrage")
class UpdateOutgoingProcessorTest {
  static final String BEISPIEL_OUTGOING_ID = "COO.1.2345.6.7891012";

  private UpdateOutgoingProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateOutgoingProcessor();
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
            für einen SOAP-Call ohne Fehler umgewandelt werden kann.
            """)
  void testeKonvertierungInSoapObjectAlleWerteVorhanden() throws Exception {
    UpdateOutgoingRequestDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap(), true);
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
                  ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    UpdateOutgoingRequestDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap(), true);
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testVorgangsreferenzNull() {
    UpdateOutgoingRequestDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2), false));
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testVorgangOhneSchriftstuecke() {
    UpdateOutgoingRequestDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null, false));
  }

  @Test
  @DisplayName(
      """
                  Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
                  """)
  void testeSonderzeichen() throws Exception {
    UpdateOutgoingRequestDTO dto = erzeugeRestDatenobjekt();

    Exchange ex = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2), true);
    stelleDatentransferAufSoapObjektSicher(dto, 2, ex.getIn().getBody());
  }

  private <T extends DMSContainerBase> T erzeugeRestDatenobjekt() {
    return (T) UpdateOutgoingRequestDTO.sample();
  }

  private Exchange prozessorAusfuehren(
      UpdateOutgoingRequestDTO dto, Map<String, DataHandler> anhaenge, boolean mitVorgangsID)
      throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, UpdateOutgoingRequestDTO.class, CreateOutgoingGI.class, anhaenge);
    if (mitVorgangsID) {
      ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_OUTGOING_ID);
    }
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateOutgoingRequestDTO dto, int anzahlAnhaenge, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof UpdateOutgoingGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getObjaddress(), equalTo(BEISPIEL_OUTGOING_ID));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getAccdef(), equalTo(dto.getAccdef()));
      assertThat(gi.getReferredincoming(), equalTo(dto.getReferredincoming()));
      assertThat(
          gi.getOutgoingdate(), equalTo(JacksonData.toXMLGregorianCalendar(dto.getOutgoingdate())));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getIncattachments(), equalTo(dto.getIncattachments()));
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));

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
