package de.muenchen.dms.search.objname;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_SEARCH_OBJ_NAME;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchObjNameResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final SearchObjNameProcessor searchObjNameProcessor;
  private final SearchObjNameResponseProcessor searchObjNameResponseProcessor;

  public SearchObjNameResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SearchObjNameProcessor searchObjNameProcessor,
      SearchObjNameResponseProcessor searchObjNameResponseProcessor) {
    super(authorizationProcessor);
    this.searchObjNameProcessor = searchObjNameProcessor;
    this.searchObjNameResponseProcessor = searchObjNameResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_SEARCH_OBJ_NAME)
        .request(searchObjNameProcessor)
        .unmarshal(SearchObjNameAnfrageDTO.class)
        .response(searchObjNameResponseProcessor);
  }
}
