package de.muenchen.dms.businessdata.update;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_BUSINESS_DATA_VALUE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessDataValueResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final UpdateBusinessDataValueProcessor requestProcessor;
  private final UpdateBusinessDataValueResponseProcessor responseProcessor;

  public UpdateBusinessDataValueResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateBusinessDataValueProcessor requestProcessor,
      UpdateBusinessDataValueResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_UPDATE_BUSINESS_DATA_VALUE)
        .request(requestProcessor)
        .unmarshal(UpdateBusinessDataValueDTO.class)
        .response(responseProcessor);
  }
}
