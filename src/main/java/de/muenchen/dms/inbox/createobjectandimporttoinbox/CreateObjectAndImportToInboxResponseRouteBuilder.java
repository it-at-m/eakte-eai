package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateObjectAndImportToInboxResponseRouteBuilder extends DmsRouteBuilder {

  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateObjectAndImportToInboxProcessor createObjectAndImportToInboxProcessor;
  private final CreateObjectAndImportToInboxResponseProcessor
      createObjectAndImportToInboxResponseProcessor;

  public CreateObjectAndImportToInboxResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateObjectAndImportToInboxProcessor createObjectAndImportToInboxProcessor,
      CreateObjectAndImportToInboxResponseProcessor createObjectAndImportToInboxResponseProcessor) {
    this.createObjectAndImportToInboxProcessor = createObjectAndImportToInboxProcessor;
    this.createObjectAndImportToInboxResponseProcessor =
        createObjectAndImportToInboxResponseProcessor;
    this.authorizationProcessor = authorizationProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_CREATE_OBJECT_AND_IMPORT_TO_INBOX)
        .routeId(RouteConstants.ROUTE_ID_CREATE_OBJECT_AND_IMPORT_TO_INBOX_VERARBEITUNG)
        .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateObjectAndImportToInboxDTO.class))
        .process(createObjectAndImportToInboxProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createObjectAndImportToInboxResponseProcessor);
  }
}
