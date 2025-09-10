package de.muenchen.dms.deposit;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class DepositResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final DepositProcessor depositProcessor;
  private final DepositResponseProcessor depositResponseProcessor;

  public DepositResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      DepositProcessor depositProcessor,
      DepositResponseProcessor depositResponseProcessor) {
    super(authorizationProcessor);
    this.depositProcessor = depositProcessor;
    this.depositResponseProcessor = depositResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_ROUTE_DEPOSIT_OBJECT)
        .request(depositProcessor)
        .response(depositResponseProcessor);
  }
}
