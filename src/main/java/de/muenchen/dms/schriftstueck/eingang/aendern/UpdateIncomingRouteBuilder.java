package de.muenchen.dms.schriftstueck.eingang.aendern;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_INCOMING;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_UPDATE_INCOMING;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class UpdateIncomingRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {

    rest("/incomings")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_UPDATE_INCOMING)
        .description("Eingang ändern")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(UpdateIncomingRequestBodyDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                UpdateIncomingAntwortDTO.class))
        .to(DIRECT_UPDATE_INCOMING);
  }
}
