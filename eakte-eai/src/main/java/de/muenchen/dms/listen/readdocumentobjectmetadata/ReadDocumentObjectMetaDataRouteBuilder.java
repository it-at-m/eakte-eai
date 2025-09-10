package de.muenchen.dms.listen.readdocumentobjectmetadata;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadDocumentObjectMetaDataRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/readDocumentObjectsMetaData")
        .get("/{" + RouteConstants.PATH_PARAM_OBJADDRESS + "}")
        .id(RouteConstants.ROUTE_ID_READ_DOCUMENT_META_DATA)
        .description("Liste von Schriftstücken mit Metadaten auslesen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .params(RestOperationDefinitionFactory.createObjectHeaderReadSubject())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadDocumentObjectMetaDataResponseDTO.class))
        .to(RouteConstants.DIRECT_READ_DOCUMENT_META_DATA);
  }
}
