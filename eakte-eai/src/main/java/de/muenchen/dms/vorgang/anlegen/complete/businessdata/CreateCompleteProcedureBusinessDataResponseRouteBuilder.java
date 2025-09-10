package de.muenchen.dms.vorgang.anlegen.complete.businessdata;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureBusinessDataResponseRouteBuilder extends DmsRouteBuilder {

  final DmsAuthorizationProcessor authorizationProcessor;
  final CreateCompleteProcedureBusinessDataProcessor createCompleteProcedureBusinessDataProcessor;
  final CreateCompleteProcedureBusinessDataResponseProcessor
      createCompleteProcedureBusinessDataResponseProcessor;

  public CreateCompleteProcedureBusinessDataResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateCompleteProcedureBusinessDataProcessor createCompleteProcedureBusinessDataProcessor,
      CreateCompleteProcedureBusinessDataResponseProcessor
          createCompleteProcedureBusinessDataResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createCompleteProcedureBusinessDataProcessor =
        createCompleteProcedureBusinessDataProcessor;
    this.createCompleteProcedureBusinessDataResponseProcessor =
        createCompleteProcedureBusinessDataResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(RouteConstants.DIRECT_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA)
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA_VERARBEITUNG)
        .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateCompleteProcedureBusinessDataBodyParams.class))
        .process(createCompleteProcedureBusinessDataProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createCompleteProcedureBusinessDataResponseProcessor);
  }
}
