package de.muenchen.dms.schriftstueck.stornieren;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CancelObjectGI;
import de.muenchen.dms.common.model.DmsErrorResponse;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CancelObjectProcessorTest {
  private CancelObjectProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CancelObjectProcessor();
  }

  @Test
  @DisplayName(
      """
        Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt für einen SOAP-Call ohne Fehler umgewandelt werden kann.
""")
  void testeKonvertierungInSoapObjektAlleWerteVorhanden() throws Exception {
    var dto = DmsErrorResponse.builder().status(0).build();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(ex.getMessage().getBody());
  }

  private Exchange prozessorAusfuehren(DmsErrorResponse dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();

    Exchange ex = TestExchanges.erzeuge(ctx);
    TestExchanges.setzeHeader(ex, CancelObjectGI.class);
    ex.getIn().setBody(dto);

    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(Object message) {
    if (message instanceof CancelObjectGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
    } else {
      Assertions.fail();
    }
  }
}
