package de.muenchen.dms.disposalschedule.aendern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetDisposalScheduleDateGI;
import de.muenchen.dms.common.route.RouteConstants;
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

public class SetDisposalScheduleDateProcessorTest {
  static final String BEISPIEL_OBJID = "COO.1.2345.6.7891012";

  private SetDisposalScheduleDateProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new SetDisposalScheduleDateProcessor();
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
                  für einen SOAP-Call ohne Fehler umgewandelt werden kann.
                  """)
  void testeKonvertierungInSoapObjectAlleWerteVorhanden() throws Exception {
    SetDisposalScheduleDateAnfrageDTO dto = erzeugeRestDatenobjekt();
    Exchange ex = prozessorAusfuehren(dto, BEISPIEL_OBJID);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                        Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
                        ein SOAP-Objekt umgewandelt werden kann.
                        """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    SetDisposalScheduleDateAnfrageDTO dto = erzeugeRestDatenobjekt();
    dto.setOffltscheduled(null);
    Exchange ex = prozessorAusfuehren(dto, BEISPIEL_OBJID);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                        Prüfe, ob ein REST-Datenbobjekt ohne ID in ein SOAP-Objekt umgewandelt werden kann.
                        """)
  void testEingangDateireferenzNull() {
    SetDisposalScheduleDateAnfrageDTO dto = erzeugeRestDatenobjekt();

    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  private SetDisposalScheduleDateAnfrageDTO erzeugeRestDatenobjekt() {
    return SetDisposalScheduleDateAnfrageDTO.sample();
  }

  private Exchange prozessorAusfuehren(SetDisposalScheduleDateAnfrageDTO dto, String Objid)
      throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, SetDisposalScheduleDateAnfrageDTO.class, SetDisposalScheduleDateGI.class);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, Objid);

    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      SetDisposalScheduleDateAnfrageDTO dto, Object message) throws DatatypeConfigurationException {
    if (message instanceof SetDisposalScheduleDateGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getObjaddress(), equalTo(BEISPIEL_OBJID));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));

      assertThat(
          gi.getOffltscheduled(),
          equalTo(JacksonData.toXMLGregorianCalendar(dto.getOffltscheduled())));

    } else {
      Assertions.fail();
    }
  }
}
