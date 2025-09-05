package de.muenchen.dms.addressee.get;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class GetAddresseeResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final GetAddresseeProcessor getAddresseeProcessor;
  private final GetAddresseeResponseProcessor getAddresseeResponseProcessor;

  public GetAddresseeResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      GetAddresseeProcessor getAddresseeProcessor,
      GetAddresseeResponseProcessor getAddresseeResponseProcessor) {
    super(authorizationProcessor);
    this.getAddresseeProcessor = getAddresseeProcessor;
    this.getAddresseeResponseProcessor = getAddresseeResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_GET_ADDRESSEE)
        .request(getAddresseeProcessor)
        .response(getAddresseeResponseProcessor);
  }
}
