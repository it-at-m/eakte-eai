package de.muenchen.dms.signature.set;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetSignatureObjectGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SetSignatureProcessorTest {
  private SetSignatureProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new SetSignatureProcessor();
  }

  @Test
  void setSignatureProcessorTest() throws Exception {
    SetSignatureRequestDTO dto = SetSignatureDTOBeispiel.erzeugeSetSignature();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void setSignatureProcessorOptionaleWerteNull() throws Exception {
    SetSignatureRequestDTO dto = new SetSignatureRequestDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(SetSignatureRequestDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(ctx, dto, SetSignatureRequestDTO.class, SetSignatureObjectGI.class);
    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSoapObjektSicher(SetSignatureRequestDTO dto, Object message) {
    if (message instanceof SetSignatureObjectGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getSignaturetype(), equalTo(dto.getSignaturetype()));
      assertThat(gi.getRemark(), equalTo(dto.getRemark()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
