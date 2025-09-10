package de.muenchen.dms.schriftstueck.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_CONTENT_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_CONTENT_OBJECT_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateContentObjectResponseRouterBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateContentObjectProcessor createContentObjectProcessor;
  private final CreateContentObjectResponseProcessor createContentObjectResponseProcessor;

  public CreateContentObjectResponseRouterBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateContentObjectProcessor createContentObjectProcessor,
      CreateContentObjectResponseProcessor createContentObjectResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createContentObjectProcessor = createContentObjectProcessor;
    this.createContentObjectResponseProcessor = createContentObjectResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_CREATE_CONTENT_OBJECT)
        .routeId(ROUTE_ID_CREATE_CONTENT_OBJECT_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateContentObjectAnfrageDTO.class))
        .process(createContentObjectProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createContentObjectResponseProcessor);
  }
}
