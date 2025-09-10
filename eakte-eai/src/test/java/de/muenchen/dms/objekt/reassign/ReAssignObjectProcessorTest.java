package de.muenchen.dms.objekt.reassign;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReAssignObjectGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReAssignObjectProcessorTest {
  private ReAssignObjectProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new ReAssignObjectProcessor();
  }

  @Test
  void testeKonvertirungInSOAPObjectAlleWerteVorhanden() throws Exception {
    ReAssignObjectDTO dto = new ReAssignObjectDTO("newTarget", "newoe");
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(ReAssignObjectDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(ctx, dto, ReAssignObjectDTO.class, ReAssignObjectGI.class);
    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSoapObjektSicher(ReAssignObjectDTO dto, Object message) {
    if (message instanceof ReAssignObjectGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getNewtarget(), equalTo(dto.getNewtarget()));
      assertThat(gi.getNewoe(), equalTo(dto.getNewoe()));
    } else {
      Assertions.fail();
    }
  }
}
