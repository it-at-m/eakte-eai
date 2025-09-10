package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateObjectAndImportToInboxRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/objectAndImportToInbox")
        .post()
        .id(RouteConstants.ROUTE_ID_CREATE_OBJECT_AND_IMPORT_TO_INBOX)
        .description("Erzeugen eines Schiftstückes in einem Postkorb")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateObjectAndImportToInboxRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateObjectAndImportToInboxResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_OBJECT_AND_IMPORT_TO_INBOX);
  }
}
