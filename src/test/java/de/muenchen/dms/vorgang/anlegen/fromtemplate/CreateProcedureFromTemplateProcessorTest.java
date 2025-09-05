package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureFromTemplateGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateProcedureFromTemplateProcessorTest {

  private CreateProcedureFromTemplateProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateProcedureFromTemplateProcessor();
  }

  @Test
  void testeKonvertierungInSOAPAlleWerteVorhanden() throws Exception {
    CreateProcedureFromTemplateRequestDTO dto =
        TestCreateProcedureFromTemplateBeispiel.erzeugeBeispiel();
    Exchange exchange = prozessAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testeKonvertierungInSOAPOptionaleWerteNull() throws Exception {
    CreateProcedureFromTemplateRequestDTO dto = new CreateProcedureFromTemplateRequestDTO();
    Exchange exchange = prozessAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessAusfuehren(CreateProcedureFromTemplateRequestDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            ctx,
            dto,
            CreateProcedureFromTemplateRequestDTO.class,
            CreateProcedureFromTemplateGI.class);
    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSOAPObjektSicher(
      CreateProcedureFromTemplateRequestDTO dto, Object message) {
    if (message instanceof CreateProcedureFromTemplateGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getReferrednumber(), equalTo(dto.getReferrednumber()));
      assertThat(gi.getTemplate(), equalTo(dto.getTemplate()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
    } else {
      Assertions.fail();
    }
  }
}
