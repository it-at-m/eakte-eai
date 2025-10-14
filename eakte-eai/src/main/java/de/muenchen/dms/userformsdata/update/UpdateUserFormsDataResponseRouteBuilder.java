package de.muenchen.dms.userformsdata.update;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFormsDataResponseRouteBuilder extends DmsRouteBuilder {

  private final DmsAuthorizationProcessor authorizationProcessor;
  private final UpdateUserFormsDataProcessor updateUserFormsDataProcessor;
  private final UpdateUserFormsDataResponseProcessor updateUserFormsDataResponseProcessor;

  public UpdateUserFormsDataResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateUserFormsDataProcessor updateUserFormsDataProcessor,
      UpdateUserFormsDataResponseProcessor updateUserFormsDataResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.updateUserFormsDataProcessor = updateUserFormsDataProcessor;
    this.updateUserFormsDataResponseProcessor = updateUserFormsDataResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_UPDATE_USER_FORMS_DATA)
        .routeId(RouteConstants.ROUTE_ID_UPDATE_USER_FORMS_DATA_VERARBEITUNG)
        .convertBodyTo(String.class)
        .process(authorizationProcessor)
        .unmarshal(getJacksonDataFormat(UpdateUserFormsDataRequestDTO.class))
        .process(updateUserFormsDataProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(updateUserFormsDataResponseProcessor);
  }
}
