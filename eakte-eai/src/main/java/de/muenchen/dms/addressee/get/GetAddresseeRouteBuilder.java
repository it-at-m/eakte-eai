package de.muenchen.dms.addressee.get;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class GetAddresseeRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/addressee")
        .get("/{" + RouteConstants.PATH_PARAM_OBJADDRESS + "}")
        .id(RouteConstants.ROUTE_ID_GET_ADDRESSEE)
        .description("Auslesen der Freitextadressaten bei Dokumenten oder Vorgängen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(RouteConstants.DIRECT_GET_ADDRESSEE);
  }
}
