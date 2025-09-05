package de.muenchen.dms.akte.anlegen.complete;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteFileResponseRouteBuilder extends DmsRouteBuilder {

  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateCompleteFileProcessor createCompleteFileProcessor;
  private final CreateCompleteFileResponseProcessor createCompleteFileResponseProcessor;

  public CreateCompleteFileResponseRouteBuilder(
      final DmsAuthorizationProcessor authorizationProcessor,
      final CreateCompleteFileProcessor createCompleteFileProcessor,
      final CreateCompleteFileResponseProcessor createCompleteFileResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createCompleteFileProcessor = createCompleteFileProcessor;
    this.createCompleteFileResponseProcessor = createCompleteFileResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_CREATE_COMPLETE_FILE)
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_FILE_VERARBEITUNG)
        .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateCompleteFileBodyParams.class))
        .process(createCompleteFileProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createCompleteFileResponseProcessor);
  }
}
