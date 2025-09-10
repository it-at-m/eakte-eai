package de.muenchen.dms.startworkflow;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.StartWorkflowGI;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StartWorkflowProcessorTest {

  private final String OBJADDRESS = "COO.1.2301.1.1042441";

  private StartWorkflowProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new StartWorkflowProcessor();
  }

  @Test
  void testeKonvertierungZuSoapAlleWerteVorhanden() throws Exception {
    StartWorkflowDTO dto = StartWorkflowDTOBeispiel.erzeugeStartWorkflow();

    Exchange exchange = prozessorAusfuehren(dto);

    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testeKonvertierungZuSoapOptionaleWerteNull() throws Exception {
    StartWorkflowDTO dto = new StartWorkflowDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(StartWorkflowDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex = TestExchanges.erzeuge(ctx, dto, StartWorkflowDTO.class, StartWorkflowGI.class);
    ex.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, OBJADDRESS);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSOAPObjektSicher(StartWorkflowDTO dto, Object message) {

    if (message instanceof StartWorkflowGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getObjaddress(), equalTo(OBJADDRESS));
      assertThat(gi.getWorkflowpattern(), equalTo(dto.getWorkflowpattern()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
    }
  }
}
