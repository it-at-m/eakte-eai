package de.muenchen.dms.schriftstueck.internal.create;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateInternalResponseRouteBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateInternalProcessor createInternalProcessor;
  private final CreateInternalResponseProcessor createInternalResponseProcessor;

  public CreateInternalResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateInternalProcessor createInternalProcessor,
      CreateInternalResponseProcessor createInternalResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createInternalProcessor = createInternalProcessor;
    this.createInternalResponseProcessor = createInternalResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_CREATE_INTERNAL)
        .id(RouteConstants.ROUTE_ID_CREATE_INTERNAL_VERARBEITUNG)
        .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateInternalBodyParams.class))
        .process(createInternalProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createInternalResponseProcessor);
  }
}
