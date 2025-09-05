package de.muenchen.dms.schriftstueck.aendern;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateContentObjectGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestDateianhaenge;
import de.muenchen.dms.test.TestExchanges;
import jakarta.activation.DataHandler;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateContentObjectProcessorTest {
  static final String BEISPIEL_INCOMING_ID = "COO.1.2345.6.7891012";

  private UpdateContentObjectProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateContentObjectProcessor();
  }

  @Test
  void testeKonvertierungInSoapObjectAlleWerteVorahnden() throws Exception {
    UpdateContentObjectDTO dto = new UpdateContentObjectDTO();

    Exchange exchange = prozessorAusfuehren(TestDateianhaenge.erzeugeMehrere(1), dto);
    stelleDatentransferAufSoapObjektSicher(dto, "anhang_0", exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(Map<String, DataHandler> anhang, UpdateContentObjectDTO dto)
      throws Exception {
    CamelContext context = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            context, dto, UpdateContentObjectDTO.class, UpdateContentObjectGI.class, anhang);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, BEISPIEL_INCOMING_ID);

    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateContentObjectDTO dto, String DATEINAME_MIT_SONDERZEICHEN, Object message) {
    if (message instanceof UpdateContentObjectGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getObjaddress(), equalTo(BEISPIEL_INCOMING_ID));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(
          gi.getGiattachmenttype().getLHMBAI151700Filename(), is(DATEINAME_MIT_SONDERZEICHEN));
      // Content comparisons fails, as Boundary is added to file during test :-(
    } else {
      Assertions.fail();
    }
  }
}
