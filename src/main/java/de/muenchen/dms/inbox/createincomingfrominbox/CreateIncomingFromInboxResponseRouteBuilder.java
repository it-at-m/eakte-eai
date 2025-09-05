package de.muenchen.dms.inbox.createincomingfrominbox;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingFromInboxResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final CreateIncomingFromInboxProcessor createIncomingFromInboxProcessor;
  private final CreateIncomingFromInboxResponseProcessor createIncomingFromInboxResponseProcessor;

  public CreateIncomingFromInboxResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateIncomingFromInboxProcessor createIncomingFromInboxProcessor,
      CreateIncomingFromInboxResponseProcessor createIncomingFromInboxResponseProcessor) {
    super(authorizationProcessor);
    this.createIncomingFromInboxProcessor = createIncomingFromInboxProcessor;
    this.createIncomingFromInboxResponseProcessor = createIncomingFromInboxResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_CREATE_INCOMING_FROM_INBOX)
        .request(createIncomingFromInboxProcessor)
        .unmarshal(CreateIncomingFromInboxRequestDTO.class)
        .response(createIncomingFromInboxResponseProcessor);
  }
}
