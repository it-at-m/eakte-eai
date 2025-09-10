package de.muenchen.dms.schriftstueck.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_CONTENT_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_CONTENT_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateContentObjectRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/contentObjects")
        .post()
        .id(ROUTE_ID_CREATE_CONTENT_OBJECT)
        .description("Schriftstück anlegen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateContentObjectRequestBodyDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateContentObjectAntwortDTO.class))
        .to(DIRECT_CREATE_CONTENT_OBJECT);
  }
}
