package de.muenchen.dms.search.objname;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchObjNameGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchObjNameProcessorTest {
  private SearchObjNameProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new SearchObjNameProcessor();
  }

  @Test
  void searchApentryProcessorTest() throws Exception {
    SearchObjNameAnfrageDTO dto = TestSearchObjNameDTOBeispiel.erzeugeSearchObjName();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void searchApentryProcessorTest2() throws Exception {
    SearchObjNameAnfrageDTO dto = new SearchObjNameAnfrageDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(SearchObjNameAnfrageDTO dto) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = TestExchanges.erzeuge(ctx);
    TestExchanges.setzeHeader(exchange, SearchObjNameGI.class);
    exchange.getMessage().setBody(dto);

    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSoapObjektSicher(SearchObjNameAnfrageDTO dto, Object message) {
    if (message instanceof SearchObjNameGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getSearchstring(), equalTo(dto.getSearchstring()));
      assertThat(gi.getObjclass(), equalTo(dto.getObjclass()));
      assertThat(gi.getReference(), equalTo(dto.getReference()));
      assertThat(gi.getValue(), equalTo(dto.getValue()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
