package de.muenchen.dms.startworkflow;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class StartWorkflowRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/startWorkflows")
        .put("/{" + RouteConstants.PATH_PARAM_OBJADDRESS + "}")
        .id(RouteConstants.ROUTE_ID_START_WORKFLOW)
        .description("Laufweg starten")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(StartWorkflowDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(RouteConstants.DIRECT_START_WORKFLOW);
  }
}
