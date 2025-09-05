/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureGI;
import de.muenchen.dms.common.util.JacksonData;
import de.muenchen.dms.test.TestExchanges;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Diese Testklasse prüft die Entgegennahme von REST-Anfragen zur Anlage von Vorgängen")
class CreateProcedureProcessorTest {

  private CreateProcedureProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateProcedureProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
        für einen SOAP-Call ohne Fehler umgewandelt werden kann.
        """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateProcedureDTO dto = TestCreateProcedureBeispiel.erzeugeFehlerlosMitUmlauten();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
            Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
            ein SOAP-Objekt umgewandelt werden kann.
            """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    CreateProcedureDTO dto = new CreateProcedureDTO();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(CreateProcedureDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(ctx, dto, CreateProcedureDTO.class, CreateProcedureGI.class);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(CreateProcedureDTO dto, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof CreateProcedureGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getReferrednumber(), equalTo(dto.getReferrednumber()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getProcremark(), equalTo(dto.getProcremark()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getAccdef(), equalTo(dto.getAccdef()));
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));
      assertThat(
          gi.getFileruntimefrom(),
          equalTo(JacksonData.toXMLGregorianCalendar(dto.getFileruntimefrom())));
      assertThat(
          gi.getFileruntimetill(),
          equalTo(JacksonData.toXMLGregorianCalendar(dto.getFileruntimetill())));
      assertThat(gi.getFiletype(), equalTo(dto.getFiletype()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
    } else {
      Assertions.fail();
    }
  }
}
