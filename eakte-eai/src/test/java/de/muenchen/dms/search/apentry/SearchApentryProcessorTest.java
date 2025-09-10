package de.muenchen.dms.search.apentry;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchApentryGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchApentryProcessorTest {

  private SearchApentryProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new SearchApentryProcessor();
  }

  @Test
  void searchApentryProcessorTest() throws Exception {
    SearchApentryDTO dto = TestSearchApentryDTOBeispiel.erzeugeSearchApentry();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void searchApentryProcessorTest2() throws Exception {
    SearchApentryDTO dto = new SearchApentryDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(SearchApentryDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = TestExchanges.erzeuge(ctx);
    TestExchanges.setzeHeader(exchange, SearchApentryGI.class);
    exchange.getMessage().setBody(dto);

    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSoapObjektSicher(SearchApentryDTO dto, Object message) {
    if (message instanceof SearchApentryGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getBasenr(), equalTo(dto.getBasenr()));
      assertThat(gi.getSubjareaspecreference(), equalTo(dto.getSubjareaspecreference()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
