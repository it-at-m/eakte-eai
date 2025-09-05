package de.muenchen.dms.akte.readapentry;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_APENTRY;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadApentryResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadApentryProcessor requestProcessor;
  private final ReadApentryResponseProcessor responseProcessor;

  public ReadApentryResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadApentryProcessor requestProcessor,
      ReadApentryResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_APENTRY).request(requestProcessor).response(responseProcessor);
  }
}
