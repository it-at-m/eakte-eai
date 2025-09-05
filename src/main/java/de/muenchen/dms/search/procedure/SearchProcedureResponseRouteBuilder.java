package de.muenchen.dms.search.procedure;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchProcedureResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final SearchProcedureProcessor searchProcedureProcessor;
  private final SearchProcedureResponseProcessor searchProcedureResponseProcessor;

  public SearchProcedureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SearchProcedureProcessor searchProcedureProcessor,
      SearchProcedureResponseProcessor searchProcedureResponseProcessor) {
    super(authorizationProcessor);
    this.searchProcedureProcessor = searchProcedureProcessor;
    this.searchProcedureResponseProcessor = searchProcedureResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_SEARCH_PROCEDURE)
        .request(searchProcedureProcessor)
        .unmarshal(SearchProcedureRequestDTO.class)
        .response(searchProcedureResponseProcessor);
  }
}
