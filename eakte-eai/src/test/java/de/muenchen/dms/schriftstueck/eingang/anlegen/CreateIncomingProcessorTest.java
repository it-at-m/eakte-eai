/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingGI;
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

/**
 * Der Prozessor wandelt das Datenobjekt, dass aus dem REST-Call befüllt wurde in ein Datenobjekt
 * für einen SOAP-Call an das DMS um.
 *
 * @see CreateIncomingBasisAnfrageDTO
 * @see CreateIncomingGI
 */
@DisplayName(
    "Die Testklasse prüft die Verarbeitung von REST-Anfragen zur Anlage von Eingangsdokumenten zu einem Vorgang")
class CreateIncomingProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~.txt";
  static final String BEISPIEL_VORGANG_COOID = "COO.1";

  private CreateIncomingBasisProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateIncomingBasisProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
        für einen SOAP-Call ohne Fehler umgewandelt werden kann.
        """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateIncomingBasisAnfrageDTO dto = erzeugeRestDatenobjekt();
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
    CreateIncomingBasisAnfrageDTO dto = new CreateIncomingBasisAnfrageDTO();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap());
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
            """)
  void testVorgangsreferenzNull() {
    CreateIncomingBasisAnfrageDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2)));
  }

  @Test
  @DisplayName(
      "Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.")
  void testVorgangOhneSchriftstuecke() {
    CreateIncomingBasisAnfrageDTO dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  @DisplayName(
      """
            Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
            """)
  void testeSonderzeichen() throws Exception {
    CreateIncomingBasisAnfrageDTO dto = CreateIncomingBasisAnfrageDTO.sample();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONDERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONDERZEICHEN, ""));

    Exchange ex = prozessorAusfuehren(dto, anhaenge);
    stelleDatentransferAufSoapObjektSicher(dto, 1, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateIncomingBasisAnfrageDTO dto, Map<String, DataHandler> anhaenge) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, CreateIncomingBasisAnfrageDTO.class, CreateIncomingGI.class, anhaenge);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_VORGANG_COOID);
    processor.process(ex);

    return ex;
  }

  private CreateIncomingBasisAnfrageDTO erzeugeRestDatenobjekt() {
    return CreateIncomingBasisAnfrageDTO.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateIncomingBasisAnfrageDTO dto, int anzahlAnhaenge, Object message) {
    if (message instanceof CreateIncomingGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getAccdef(), equalTo(dto.getAccdef()));
      assertThat(gi.getForeignnr(), equalTo(dto.getForeignnr()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getDocumentremarks(), equalTo(dto.getDocumentremarks()));
      assertThat(gi.getIncattachments(), equalTo(dto.getIncattachments()));
      assertThat(gi.getDefinition(), equalTo(dto.getDefinition()));
      assertThat(gi.getUserformsdata(), equalTo(dto.getUserformsdata()));
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));

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
