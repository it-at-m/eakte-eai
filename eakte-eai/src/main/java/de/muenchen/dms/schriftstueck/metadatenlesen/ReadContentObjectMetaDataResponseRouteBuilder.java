package de.muenchen.dms.schriftstueck.metadatenlesen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_CONTENT_OBJECT_META_DATA;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectMetaDataResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadContentObjectMetaDataProcessor requestProcessor;
  private final ReadContentObjectMetaDataResponseProcessor responseProcessor;

  public ReadContentObjectMetaDataResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadContentObjectMetaDataProcessor requestProcessor,
      ReadContentObjectMetaDataResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_CONTENT_OBJECT_META_DATA)
        .request(requestProcessor)
        .response(responseProcessor);
  }
}
