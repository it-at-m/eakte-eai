package de.muenchen.dms.schriftstueck.ausgang.lesen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_OUTGOING;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadOutgoingRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/outgoings")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_OUTGOING)
        .description("Ausgang lesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadOutgoingAntwortDTO.class))
        .to(RouteConstants.DIRECT_READ_OUTGOING);
  }
}
