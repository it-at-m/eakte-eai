package de.muenchen.dms.businessdata.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateBusinessDataValueGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateBusinessDataValueProcessorTest {
  private UpdateBusinessDataValueProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateBusinessDataValueProcessor();
  }

  @Test
  void testProcessor1() throws Exception {
    UpdateBusinessDataValueDTO dto = createSampleDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testProcessor2() throws Exception {
    UpdateBusinessDataValueDTO dto = new UpdateBusinessDataValueDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(UpdateBusinessDataValueDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();

    Exchange ex = TestExchanges.erzeuge(ctx);
    TestExchanges.setzeHeader(ex, UpdateBusinessDataValueGI.class);
    ex.getMessage().setBody(dto);

    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateBusinessDataValueDTO dto, Object message) {
    if (message instanceof UpdateBusinessDataValueGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getValue(), equalTo(dto.getValue()));
      assertThat(gi.getReference(), equalTo(dto.getReference()));
    } else {
      Assertions.fail();
    }
  }

  private UpdateBusinessDataValueDTO createSampleDTO() {
    return UpdateBusinessDataValueDTO.builder().value("1234").reference("dog_number").build();
  }
}
