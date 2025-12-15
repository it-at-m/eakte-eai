/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateOutgoingGI;
import de.muenchen.dms.common.model.DMSContainerBase;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import jakarta.activation.DataHandler;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * Der Prozessor wandelt das Datenobjekt, dass aus dem REST-Call befüllt wurde in ein Datenobjekt
 * für einen SOAP-Call an das DMS um.
 *
 * @see CreateOutgoingAnfrageDTO
 * @see CreateOutgoingGI
 */
@DisplayName("Die Testklasse prüft die Verarbeitung von REST-Anfragen")
class CreateOutgoingProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~.txt";
  static final String BEISPIEL_VORGANG_ID = "COO.1.2301.1.1042432";

  private CreateOutgoingProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateOutgoingProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
        für einen SOAP-Call ohne Fehler umgewandelt werden kann.
        """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateOutgoingAnfrageDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2), true);
    stelleDatentransferAufSoapObjektSicher(dto, 2, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
            ein SOAP-Objekt umgewandelt werden kann.
            """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    CreateOutgoingAnfrageDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap(), true);
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
            """)
  void testVorgangsreferenzNull() {
    CreateOutgoingAnfrageDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2), false));
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.
            """)
  void testVorgangOhneSchriftstuecke() throws Exception {
    CreateOutgoingAnfrageDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null, false));
  }

  @Test
  @DisplayName(
      """
            Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
            """)
  void testeSonderzeichen() throws Exception {
    CreateOutgoingAnfrageDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2), true);
    stelleDatentransferAufSoapObjektSicher(dto, 2, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateOutgoingAnfrageDTO dto, Map<String, DataHandler> anhaenge, boolean mitVorgangsID)
      throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, CreateOutgoingAnfrageDTO.class, CreateOutgoingGI.class, anhaenge);
    if (mitVorgangsID) {
      ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_VORGANG_ID);
    }
    processor.process(ex);
    return ex;
  }

  private <T extends DMSContainerBase> T erzeugeRestDatenobjekt() {
    return (T) CreateOutgoingAnfrageDTO.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateOutgoingAnfrageDTO dto, int anzahlAnhaenge, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof CreateOutgoingGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getReferrednumber(), equalTo(BEISPIEL_VORGANG_ID));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getAccdef(), equalTo(dto.getAccdef()));
      assertThat(gi.getReferredincoming(), equalTo(dto.getReferredincoming()));
      assertThat(
          gi.getOutgoingdate(), equalTo(JacksonData.toXMLGregorianCalendar(dto.getOutgoingdate())));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getSubfiletype(), equalTo(dto.getSubfiletype()));
      assertThat(gi.getDoctemplate(), equalTo(dto.getDoctemplate()));
      assertThat(gi.getIncattachments(), equalTo(dto.getIncattachments()));
      assertThat(gi.getSearchalso(), nullValue());
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));
      assertThat(gi.getDefinition(), equalTo(dto.getDefinition()));
      assertThat(gi.getUserformsdata(), equalTo(dto.getUserformsdata()));

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
