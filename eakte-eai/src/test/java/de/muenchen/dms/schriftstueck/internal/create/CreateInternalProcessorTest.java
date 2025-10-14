package de.muenchen.dms.schriftstueck.internal.create;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateInternalGI;
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

class CreateInternalProcessorTest {

  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~.txt";
  static final String BEISPIEL_VORGANG_COOID = "COO.1";

  private CreateInternalProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateInternalProcessor();
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
                  für einen SOAP-Call ohne Fehler umgewandelt werden kann.
                  """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateInternalBodyParams dto = erzeugeRestDatenobjekt();
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
    CreateInternalBodyParams dto = new CreateInternalBodyParams();
    Exchange ex = prozessorAusfuehren(dto, Collections.emptyMap());
    stelleDatentransferAufSoapObjektSicher(dto, 0, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                      Prüfe, ob ein REST-Datenbobjekt ohne Vorgangs-ID in ein SOAP-Objekt umgewandelt werden kann.
                      """)
  void testVorgangsreferenzNull() {
    CreateInternalBodyParams dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, TestDateianhaenge.erzeugeMehrere(2)));
  }

  @Test
  @DisplayName(
      "Prüfe, ob ein REST-Datenobjekt ohne Schriftstücke in ein SOAP-Objekt umgewandelt werden kann.")
  void testVorgangOhneSchriftstuecke() {
    CreateInternalBodyParams dto = erzeugeRestDatenobjekt();
    assertDoesNotThrow(() -> prozessorAusfuehren(dto, null));
  }

  @Test
  @DisplayName(
      """
                      Prüfe den korrekten Umgang mit Sonderzeichen im Anhang einer REST-Nachricht.
                      """)
  void testeSonderzeichen() throws Exception {
    CreateInternalBodyParams dto = CreateInternalBodyParams.sample();
    Map<String, DataHandler> anhaenge = new HashMap<>();

    anhaenge.put(
        DATEINAME_MIT_SONDERZEICHEN, TestDateianhaenge.erzeuge(DATEINAME_MIT_SONDERZEICHEN, ""));

    Exchange ex = prozessorAusfuehren(dto, anhaenge);
    stelleDatentransferAufSoapObjektSicher(dto, 1, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(
      CreateInternalBodyParams dto, Map<String, DataHandler> anhaenge) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, CreateInternalBodyParams.class, CreateInternalGI.class, anhaenge);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_VORGANG_COOID);
    processor.process(ex);

    return ex;
  }

  private CreateInternalBodyParams erzeugeRestDatenobjekt() {
    return CreateInternalBodyParams.sample();
  }

  private void stelleDatentransferAufSoapObjektSicher(
      CreateInternalBodyParams dto, int anzahlAnhaenge, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof CreateInternalGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getReferrednumber(), equalTo(dto.getReferrednumber()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(
          gi.getDeliverydate(), equalTo(JacksonData.toXMLGregorianCalendar(dto.getDeliverydate())));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getSubfiletype(), equalTo(dto.getSubfiletype()));
      assertThat(gi.getDoctemplate(), equalTo(dto.getDoctemplate()));
      assertThat(gi.getIncattachments(), equalTo(dto.getIncattachments()));
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
