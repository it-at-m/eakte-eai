package de.muenchen.dms.userformsdata.read;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadUserFormsDataResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadUserFormsDataProcessor readUserFormsDataProcessor;
  private final ReadUserFormsDataResponseProcessor readUserFormsDataResponseProcessor;

  public ReadUserFormsDataResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadUserFormsDataProcessor readUserFormsDataProcessor,
      ReadUserFormsDataResponseProcessor readUserFormsDataResponseProcessor) {
    super(authorizationProcessor);
    this.readUserFormsDataProcessor = readUserFormsDataProcessor;
    this.readUserFormsDataResponseProcessor = readUserFormsDataResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_READ_USER_FORMS_DATA)
        .request(readUserFormsDataProcessor)
        .response(readUserFormsDataResponseProcessor);
  }
}
