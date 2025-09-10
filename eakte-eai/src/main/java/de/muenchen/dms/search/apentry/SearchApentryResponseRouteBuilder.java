package de.muenchen.dms.search.apentry;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_SEARCH_APENTRY;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchApentryResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final SearchApentryProcessor searchApentryProcessor;
  private final SearchApentryResponseProcessor searchApentryResponseProcessor;

  public SearchApentryResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SearchApentryProcessor searchApentryProcessor,
      SearchApentryResponseProcessor searchApentryResponseProcessor) {
    super(authorizationProcessor);
    this.searchApentryProcessor = searchApentryProcessor;
    this.searchApentryResponseProcessor = searchApentryResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_SEARCH_APENTRY)
        .request(searchApentryProcessor)
        .unmarshal(SearchApentryDTO.class)
        .response(searchApentryResponseProcessor);
  }
}
