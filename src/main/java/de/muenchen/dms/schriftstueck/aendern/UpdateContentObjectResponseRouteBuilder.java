package de.muenchen.dms.schriftstueck.aendern;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_CONTENT_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_CONTENT_OBJECT_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateContentObjectResponseRouteBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final UpdateContentObjectProcessor updateContentObjectProcessor;
  private final UpdateContentObjectResponseProcessor updateContentObjectResponseProcessor;

  public UpdateContentObjectResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateContentObjectProcessor updateContentObjectProcessor,
      UpdateContentObjectResponseProcessor updateContentObjectResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.updateContentObjectProcessor = updateContentObjectProcessor;
    this.updateContentObjectResponseProcessor = updateContentObjectResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_UPDATE_CONTENT_OBJECT)
        .routeId(ROUTE_ID_UPDATE_CONTENT_OBJECT_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .choice()
        .when(simple("${body} != null"))
        .unmarshal(getJacksonDataFormat(UpdateContentObjectDTO.class))
        .process(updateContentObjectProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(updateContentObjectResponseProcessor)
        .otherwise()
        .process(updateContentObjectProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(updateContentObjectResponseProcessor)
        .end();
  }
}
