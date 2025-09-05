package de.muenchen.dms.aktenplaneintrag.anlegen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateSubjectAreaUnitGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CreateSubjectAreaUnitProcessorTest {
  static final String UEBERGEORDNETEN_AKTENPLANEINTRAGS_COOID = "COO.1.2302.1.8";

  private CreateSubjectAreaUnitProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateSubjectAreaUnitProcessor();
  }

  @Test
  @DisplayName(
      """
       Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
       für einen SOAP-Call ohne Fehler umgewandelt werden kann.
       """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateSubjectAreaUnitAnfrageDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
      Prüfe, ob ein REST-Datenbobjekt ohne Objaddress in ein SOAP-Objekt umgewandelt werden kann.
      """)
  void testAktePlanEintragferenzNull() {
    CreateSubjectAreaUnitAnfrageDTO dto = erzeugeRestDatenobjekt();
    dto.setObjaddress("");
    assertDoesNotThrow(() -> prozessorAusfuehren(dto));
  }

  @Test
  @DisplayName(
      "Prüfe, ob ein REST-Datenobjekt ohne Basenr in ein SOAP-Objekt umgewandelt werden kann.")
  void testOhneBasenr() {
    CreateSubjectAreaUnitAnfrageDTO dto = erzeugeRestDatenobjekt();
    dto.setBasenr("");
    assertDoesNotThrow(() -> prozessorAusfuehren(dto));
  }

  private Exchange prozessorAusfuehren(CreateSubjectAreaUnitAnfrageDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, CreateSubjectAreaUnitAnfrageDTO.class, CreateSubjectAreaUnitGI.class);
    ex.getIn()
        .setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, UEBERGEORDNETEN_AKTENPLANEINTRAGS_COOID);
    processor.process(ex);

    return ex;
  }

  private CreateSubjectAreaUnitAnfrageDTO erzeugeRestDatenobjekt() {
    return CreateSubjectAreaUnitAnfrageDTO.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateSubjectAreaUnitAnfrageDTO dto, Object message) {
    if (message instanceof CreateSubjectAreaUnitGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getObjaddress(), equalTo(dto.getObjaddress()));
      assertThat(gi.getBasenr(), equalTo(dto.getBasenr()));
      assertThat(gi.getSubjareaspecreference(), equalTo(dto.getSubjareaspecreference()));
      assertThat(gi.getShortterm(), equalTo(dto.getShortterm()));
      assertThat(gi.getSubjarchiveschedule(), equalTo(dto.getSubjarchiveschedule()));
      assertThat(gi.getSubjschedule(), equalTo(dto.getSubjschedule()));
      assertThat(gi.getSubjdispstate(), equalTo(dto.getSubjdispstate()));
      assertThat(gi.getFileaccessdefinition(), equalTo(dto.getFileaccessdefinition()));
    } else {
      Assertions.fail();
    }
  }
}
