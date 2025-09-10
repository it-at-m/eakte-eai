package de.muenchen.dms.schriftstueck.ausgang.lesen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_OUTGOING;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadOutgoingResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadOutgoingProcessor requestProcessor;
  private final ReadOutgoingResponseProcessor responseProcessor;

  public ReadOutgoingResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadOutgoingProcessor requestProcessor,
      ReadOutgoingResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_OUTGOING).request(requestProcessor).response(responseProcessor);
  }
}
