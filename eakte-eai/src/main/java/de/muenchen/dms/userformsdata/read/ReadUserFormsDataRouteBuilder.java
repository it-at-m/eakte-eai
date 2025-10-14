package de.muenchen.dms.userformsdata.read;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.model.rest.RestParamType;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadUserFormsDataRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/userFormsData")
        .get("/{" + RouteConstants.PATH_PARAM_OBJADDRESS + "}")
        .id(RouteConstants.ROUTE_ID_READ_USER_FORMS_DATA)
        .description("Aktenübersicht auslesen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .param()
        .name("reference")
        .type(RestParamType.query)
        .required(true)
        .endParam()
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadUserFormsDataResponseDTO.class))
        .to(RouteConstants.DIRECT_READ_USER_FORMS_DATA);
  }
}
