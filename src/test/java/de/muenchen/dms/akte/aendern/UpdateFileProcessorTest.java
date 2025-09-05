/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.aendern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateFileGI;
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

@DisplayName("Diese Testklasse prüft die Entgegennahme von REST-Anfragen zur Anlage von Akten")
class UpdateFileProcessorTest {

  private UpdateFileProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateFileProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
        für einen SOAP-Call ohne Fehler umgewandelt werden kann.
        """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    UpdateFileDTO dto = TestUpdateFileExample.erzeugeFehlerlosMitUmlauten();
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
    UpdateFileDTO dto = new UpdateFileDTO();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(UpdateFileDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex = TestExchanges.erzeuge(ctx, dto, UpdateFileDTO.class, UpdateFileGI.class);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(UpdateFileDTO dto, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof UpdateFileGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));
      assertThat(gi.getProcedureaccdef(), equalTo(dto.getProcedureaccdef()));
      assertThat(
          gi.getFileruntimefrom(),
          equalTo(JacksonData.toXMLGregorianCalendar(dto.getFileruntimefrom())));
      assertThat(
          gi.getFileruntimetill(),
          equalTo(JacksonData.toXMLGregorianCalendar(dto.getFileruntimetill())));
      assertThat(
          gi.getFilearchivetimeOffltdeadline(), equalTo(dto.getFilearchivetimeOffltdeadline()));
      assertThat(gi.getFilelifetimeOffltdeadline(), equalTo(dto.getFilelifetimeOffltdeadline()));
      assertThat(gi.getOffltdispstate(), equalTo(dto.getOffltdispstate()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
    } else {
      Assertions.fail();
    }
  }
}
