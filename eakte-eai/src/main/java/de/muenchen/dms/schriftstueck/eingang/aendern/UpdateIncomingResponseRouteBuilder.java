package de.muenchen.dms.schriftstueck.eingang.aendern;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_INCOMING;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_INCOMING_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateIncomingResponseRouteBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final UpdateIncomingProcessor updateIncomingProcessor;
  private final UpdateIncomingResponseProcessor updateIncomingResponseProcessor;

  public UpdateIncomingResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateIncomingProcessor updateIncomingProcessor,
      UpdateIncomingResponseProcessor updateIncomingResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.updateIncomingProcessor = updateIncomingProcessor;
    this.updateIncomingResponseProcessor = updateIncomingResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_UPDATE_INCOMING)
        .routeId(ROUTE_ID_UPDATE_INCOMING_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(UpdateIncomingAnfrageDTO.class))
        .process(updateIncomingProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(updateIncomingResponseProcessor);
  }
}
