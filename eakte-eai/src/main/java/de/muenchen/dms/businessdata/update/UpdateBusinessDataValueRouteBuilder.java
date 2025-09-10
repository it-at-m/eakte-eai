package de.muenchen.dms.businessdata.update;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_BUSINESS_DATA_VALUE;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_BUSINESS_DATA_VALUE;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessDataValueRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/businessDataValue")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_UPDATE_BUSINESS_DATA_VALUE)
        .description("Setzen/Aktualisieren von Fachdaten")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(UpdateBusinessDataValueDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(DIRECT_UPDATE_BUSINESS_DATA_VALUE);
  }
}
