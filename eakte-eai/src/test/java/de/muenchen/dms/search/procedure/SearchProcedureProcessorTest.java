package de.muenchen.dms.search.procedure;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchProcedureGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SearchProcedureProcessorTest {

  private SearchProcedureProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new SearchProcedureProcessor();
  }

  @Test
  void searchProcedureAlleWerteVorhanden() throws Exception {
    SearchProcedureRequestDTO dto = SearchProcedureDTOBeispiel.erzeugeSearchProcedure();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void searchProcedureOptionaleWerteNull() throws Exception {
    SearchProcedureRequestDTO dto = new SearchProcedureRequestDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(SearchProcedureRequestDTO dto) throws Exception {
    CamelContext context = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            context, dto, SearchProcedureRequestDTO.class, SearchProcedureGI.class);
    processor.process(exchange);
    return exchange;
  }

  private void stelleDatentransferAufSOAPObjektSicher(
      SearchProcedureRequestDTO dto, Object message) {
    if (message instanceof SearchProcedureGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getReferrednumber(), equalTo(dto.getReferrednumber()));
      assertThat(gi.getFilereference(), equalTo(dto.getFilereference()));
      assertThat(gi.getObjmlname(), equalTo(dto.getObjmlname()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
