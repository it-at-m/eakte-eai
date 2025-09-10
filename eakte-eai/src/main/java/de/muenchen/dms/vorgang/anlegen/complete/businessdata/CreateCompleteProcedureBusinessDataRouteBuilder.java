package de.muenchen.dms.vorgang.anlegen.complete.businessdata;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureBusinessDataRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/completeProcedureBusinessData")
        .post()
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA)
        .description("Anlegen eines kompletten Vorgangs mit Fachdaten")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateCompleteProcedureBusinessDataRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateCompleteProcedureBusinessDataResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA);
  }
}
