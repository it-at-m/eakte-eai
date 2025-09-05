package de.muenchen.dms.objekt.reassign;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_REASSIGN_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_REASSIGN_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReAssignObjectRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/reAssignObject")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_REASSIGN_OBJECT)
        .description("Objekt umschreiben")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(ReAssignObjectDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(DIRECT_REASSIGN_OBJECT);
  }
}
