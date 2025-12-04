package de.muenchen.dms.userformsdata.read;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadUserFormsDataGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ReadUserFormsDataProcessorTest {

  private ReadUserFormsDataProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadUserFormsDataProcessor();
  }

  @Test
  void readUserFormsDataAlleWerteVorhanden() throws Exception {
    ReadUserFormsDataRequestDTO dto = ReadUserFormsDataDTOBeispiel.erzeugeReadUserFormsData();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void readUserFormsDataOptionaleWerteNull() throws Exception {
    ReadUserFormsDataRequestDTO dto = new ReadUserFormsDataRequestDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(ReadUserFormsDataRequestDTO dto) throws Exception {
    CamelContext context = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            context, dto, ReadUserFormsDataRequestDTO.class, ReadUserFormsDataGI.class);

    exchange.getIn().setHeader("reference", dto.getReference());
    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSOAPObjektSicher(
      ReadUserFormsDataRequestDTO dto, Object message) {
    if (message instanceof ReadUserFormsDataGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getReference(), equalTo(dto.getReference()));
    }
  }
}
