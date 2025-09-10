package de.muenchen.dms.schriftstueck.ausgang.update;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_OUTGOING;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_OUTGOING_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateOutgoingResponseRouteBuilder extends DmsRouteBuilder {

  private final DmsAuthorizationProcessor authorizationProcessor;
  private final UpdateOutgoingProcessor updateOutgoingProcessor;
  private final UpdateOutgoingResponseProcessor updateOutgoingResponseProcessor;

  public UpdateOutgoingResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateOutgoingProcessor updateOutgoingProcessor,
      UpdateOutgoingResponseProcessor updateOutgoingResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.updateOutgoingProcessor = updateOutgoingProcessor;
    this.updateOutgoingResponseProcessor = updateOutgoingResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_UPDATE_OUTGOING)
        .routeId(ROUTE_ID_UPDATE_OUTGOING_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(UpdateOutgoingRequestDTO.class))
        .process(updateOutgoingProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(updateOutgoingResponseProcessor);
  }
}
