package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureFromTemplateRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/proceduresFromTemplate")
        .post()
        .id(RouteConstants.ROUTE_ID_CREATE_PROCEDURE_FROM_TEMPLATE)
        .description("Erzeugen von Vorgängen auf Basis einer Vorgangsvorlage")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateProcedureFromTemplateRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateProcedureFromTemplateResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_PROCEDURE_FROM_TEMPLATE);
  }
}
