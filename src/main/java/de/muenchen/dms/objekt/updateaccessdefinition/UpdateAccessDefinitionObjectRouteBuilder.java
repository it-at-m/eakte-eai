package de.muenchen.dms.objekt.updateaccessdefinition;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_ACCESS_DEFINITION_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_ACCESS_DEFINITION_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UpdateAccessDefinitionObjectRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/accessDefinitions")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_UPDATE_ACCESS_DEFINITION_OBJECT)
        .description("Zugriffsdefinition des angegebenen Objektes ändern")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(UpdateAccessDefinitionObjectAnfrageDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(DIRECT_UPDATE_ACCESS_DEFINITION_OBJECT);
  }
}
