package de.muenchen.dms.vorgang.anlegen.complete;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/procedures")
        .post("complete")
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_PROCEDURE)
        .description("Anlegen eines kompletten Vorgangs")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateCompleteProcedureRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateCompleteProcedureResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_COMPLETE_PROCEDURE);
  }
}
