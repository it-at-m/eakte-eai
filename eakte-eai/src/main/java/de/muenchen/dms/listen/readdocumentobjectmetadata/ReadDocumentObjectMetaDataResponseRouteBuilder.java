package de.muenchen.dms.listen.readdocumentobjectmetadata;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadDocumentObjectMetaDataResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadDocumentObjectMetaDataProcessor requestProcessor;
  private final ReadDocumentObjectMetaDataResponseProcessor responseProcessor;

  public ReadDocumentObjectMetaDataResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadDocumentObjectMetaDataProcessor requestProcessor,
      ReadDocumentObjectMetaDataResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_READ_DOCUMENT_META_DATA)
        .request(requestProcessor)
        .response(responseProcessor);
  }
}
