package de.muenchen.dms.addressee.add;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_ADD_ADDRESSEE;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_ADD_ADDRESSEE;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class AddAddresseeRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/addAddressees")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_ADD_ADDRESSEE)
        .description("Hinzufügen eines Freitextadressaten bei Dokumenten oder Vorgängen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(AddAddresseeRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(DIRECT_ADD_ADDRESSEE);
  }
}
