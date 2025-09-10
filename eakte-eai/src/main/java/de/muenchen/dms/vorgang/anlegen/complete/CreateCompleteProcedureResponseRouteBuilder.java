package de.muenchen.dms.vorgang.anlegen.complete;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureResponseRouteBuilder extends DmsRouteBuilder {

  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateCompleteProcedureProcessor createCompleteProcedureProcessor;
  private final CreateCompleteProcedureResponseProcessor createCompleteProcedureResponseProcessor;

  public CreateCompleteProcedureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateCompleteProcedureProcessor createCompleteProcedureProcessor,
      CreateCompleteProcedureResponseProcessor createCompleteProcedureResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createCompleteProcedureProcessor = createCompleteProcedureProcessor;
    this.createCompleteProcedureResponseProcessor = createCompleteProcedureResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_CREATE_COMPLETE_PROCEDURE)
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_PROCEDURE_VERARBEITUNG)
        .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateCompleteProcedureBodyParams.class))
        .process(createCompleteProcedureProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createCompleteProcedureResponseProcessor);
  }
}
