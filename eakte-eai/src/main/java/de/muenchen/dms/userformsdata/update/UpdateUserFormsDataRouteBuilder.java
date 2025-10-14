package de.muenchen.dms.userformsdata.update;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFormsDataRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/userFormsData")
        .put("/{" + RouteConstants.PATH_PARAM_OBJADDRESS + "}")
        .id(RouteConstants.ROUTE_ID_UPDATE_USER_FORMS_DATA)
        .description("UserFormsData ändern")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(UpdateUserFormsDataRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(RestOperationDefinitionFactory.createBaseResponseMsgDefinition())
        .to(RouteConstants.DIRECT_UPDATE_USER_FORMS_DATA);
  }
}
