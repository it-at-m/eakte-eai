package de.muenchen.dms.search.file;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SearchFileResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final SearchFileProcessor searchFileProcessor;
  private final SearchFileResponseProcessor searchFileResponseProcessor;

  public SearchFileResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SearchFileProcessor searchFileProcessor,
      SearchFileResponseProcessor searchFileResponseProcessor) {
    super(authorizationProcessor);
    this.searchFileProcessor = searchFileProcessor;
    this.searchFileResponseProcessor = searchFileResponseProcessor;
  }

  @Override
  public void route() throws Exception {

    soap(RouteConstants.DIRECT_SEARCH_FILE)
        .request(searchFileProcessor)
        .unmarshal(SearchFileRequestDTO.class)
        .response(searchFileResponseProcessor);
  }
}
