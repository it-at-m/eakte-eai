package de.muenchen.dms.search.procedure;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchProcedureGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchProcedureProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final SearchProcedureRequestDTO dto = exchange.getIn().getBody(SearchProcedureRequestDTO.class);

    final SearchProcedureGI parameters =
        searchProcedure(
            getNutzer(exchange),
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, SearchProcedureGI.class);
  }

  private SearchProcedureGI searchProcedure(
      final String userlogin,
      final SearchProcedureRequestDTO dto,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    final SearchProcedureGI searchProcedureGI = objectFactory.createSearchProcedureGI();
    searchProcedureGI.setUserlogin(userlogin);
    searchProcedureGI.setReferrednumber(dto.getReferrednumber());
    searchProcedureGI.setFilereference(dto.getFilereference());
    searchProcedureGI.setObjmlname(dto.getObjmlname());
    searchProcedureGI.setFilesubj(dto.getFilesubj());
    searchProcedureGI.setJobposition(jobposition);
    searchProcedureGI.setJoboe(joboe);
    searchProcedureGI.setBusinessapp(businessapp);

    return searchProcedureGI;
  }
}
