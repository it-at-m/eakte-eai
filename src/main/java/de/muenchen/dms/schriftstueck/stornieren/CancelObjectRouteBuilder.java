package de.muenchen.dms.schriftstueck.stornieren;

import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CANCEL_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CancelObjectRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/cancelObjects")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_CANCEL_OBJECT)
        .description("Schriftstück stornieren")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(RouteConstants.DIRECT_CANCEL_OBJECT);
  }
}
