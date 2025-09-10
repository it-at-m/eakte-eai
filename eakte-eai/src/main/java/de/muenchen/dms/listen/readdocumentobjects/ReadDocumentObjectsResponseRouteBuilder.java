package de.muenchen.dms.listen.readdocumentobjects;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_DOCUMENT_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadDocumentObjectsResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadDocumentObjectsProcessor requestProcessor;
  private final ReadDocumentObjectsResponseProcessor responseProcessor;

  public ReadDocumentObjectsResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadDocumentObjectsProcessor requestProcessor,
      ReadDocumentObjectsResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_DOCUMENT_OBJECT).request(requestProcessor).response(responseProcessor);
  }
}
