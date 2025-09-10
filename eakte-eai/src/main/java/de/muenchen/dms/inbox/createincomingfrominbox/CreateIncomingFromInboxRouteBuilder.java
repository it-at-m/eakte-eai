package de.muenchen.dms.inbox.createincomingfrominbox;

import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingFromInboxRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/incomingFromInbox")
        .post()
        .id(RouteConstants.ROUTE_ID_CREATE_INCOMING_FROM_INBOX)
        .description("Erfassen eines Eingangs aus dem Postkorb")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateIncomingFromInboxRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(DmsObjektResponse.class))
        .to(RouteConstants.DIRECT_CREATE_INCOMING_FROM_INBOX);
  }
}
