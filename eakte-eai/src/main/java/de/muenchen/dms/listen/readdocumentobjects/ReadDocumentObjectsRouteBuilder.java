package de.muenchen.dms.listen.readdocumentobjects;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_DOCUMENT_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_DOCUMENT_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadDocumentObjectsRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/documentObjects")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_DOCUMENT_OBJECT)
        .description("Liste von Schriftstücken auslesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .params(RestOperationDefinitionFactory.createObjectHeaderReadSubject())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadDocumentObjectsAntwortDTO.class))
        .to(DIRECT_READ_DOCUMENT_OBJECT);
  }
}
