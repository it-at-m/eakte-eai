package de.muenchen.dms.akte.anlegen.complete;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteFileRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/files")
        .post("complete")
        .id(RouteConstants.ROUTE_ID_CREATE_COMPLETE_FILE)
        .description("Anlegen einer kompletten Akte")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateCompleteFileRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateCompleteFileResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_COMPLETE_FILE);
  }
}
