package de.muenchen.dms.search.file;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchFileGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchFileProcessorTest {

  private SearchFileProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new SearchFileProcessor();
  }

  @Test
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    SearchFileRequestDTO dto = TestSearchFileDTOBeispiel.erzeugeSearchFile();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    SearchFileRequestDTO dto = new SearchFileRequestDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(SearchFileRequestDTO dto) throws Exception {
    CamelContext context = new DefaultCamelContext();
    Exchange exchange = TestExchanges.erzeuge(context);
    TestExchanges.setzeHeader(exchange, SearchFileGI.class);
    exchange.getMessage().setBody(dto);

    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSOAPObjektSicher(SearchFileRequestDTO dto, Object message) {
    if (message instanceof SearchFileGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getApentry(), equalTo(dto.getApentry()));
      assertThat(gi.getFilereference(), equalTo(dto.getFilereference()));
      assertThat(gi.getObjmlname(), equalTo(dto.getObjmlname()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
