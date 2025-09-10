package de.muenchen.dms.aktenplaneintrag.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateSubjectAreaGI;
import de.muenchen.dms.test.TestExchanges;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName(
    "Diese Testklasse prüft die Entgegennahme von REST-Anfragen zum ändern des Aktenplaneintragtyps")
public class UpdateSubjectAreaProcessorTest {

  private UpdateSubjectAreaProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateSubjectAreaProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
        für einen SOAP-Call ohne Fehler umgewandelt werden kann.
        """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    UpdateSubjectAreaRequestDTO dto = TestUpdateSubjectAreaExample.erzeugeAntwort();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(UpdateSubjectAreaRequestDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, UpdateSubjectAreaRequestDTO.class, UpdateSubjectAreaGI.class);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateSubjectAreaRequestDTO dto, Object message) throws DatatypeConfigurationException {
    if (message instanceof UpdateSubjectAreaGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getSubjareascope(), equalTo(dto.getSubjareascope()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
    } else {
      Assertions.fail();
    }
  }
}
