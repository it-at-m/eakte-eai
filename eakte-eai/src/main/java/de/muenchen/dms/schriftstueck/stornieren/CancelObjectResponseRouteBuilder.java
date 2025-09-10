package de.muenchen.dms.schriftstueck.stornieren;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CANCEL_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CancelObjectResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final CancelObjectProcessor requestProcessor;
  private final CancelObjectResponseProcessor responseProcessor;

  public CancelObjectResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CancelObjectProcessor requestProcessor,
      CancelObjectResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_CANCEL_OBJECT).request(requestProcessor).response(responseProcessor);
  }
}
