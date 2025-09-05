package de.muenchen.dms.deposit.revoke;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_REVOKE_DEPOSIT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class RevokeDepositResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final RevokeDepositProcessor revokeDepositProcessor;
  private final RevokeDepositResponseProcessor revokeDepositResponseProcessor;

  public RevokeDepositResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      RevokeDepositProcessor revokeDepositProcessor,
      RevokeDepositResponseProcessor revokeDepositResponseProcessor) {
    super(authorizationProcessor);
    this.revokeDepositProcessor = revokeDepositProcessor;
    this.revokeDepositResponseProcessor = revokeDepositResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_REVOKE_DEPOSIT)
        .request(revokeDepositProcessor)
        .response(revokeDepositResponseProcessor);
  }
}
