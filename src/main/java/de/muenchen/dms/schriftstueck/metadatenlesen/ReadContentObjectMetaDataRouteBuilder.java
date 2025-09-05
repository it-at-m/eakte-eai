package de.muenchen.dms.schriftstueck.metadatenlesen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_CONTENT_OBJECT_META_DATA;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectMetaDataRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/contentObjectMetaData")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_CONTENT_OBJECT_META_DATA)
        .description("Schriftstück Metadaten lesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadContentObjectMetaDataAntwortDTO.class))
        .to(RouteConstants.DIRECT_READ_CONTENT_OBJECT_META_DATA);
  }
}
