package de.muenchen.dms.objekt.updateaccessdefinition;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_ACCESS_DEFINITION_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateAccessDefinitionObjectResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final UpdateAccessDefinitionObjectProcessor updateAccessDefinitionObjectProcessor;
  private final UpdateAccessDefinitionObjectResponseProcessor
      updateAccessDefinitionObjectResponseProcessor;

  public UpdateAccessDefinitionObjectResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateAccessDefinitionObjectProcessor updateAccessDefinitionObjectProcessor,
      UpdateAccessDefinitionObjectResponseProcessor updateAccessDefinitionObjectResponseProcessor) {
    super(authorizationProcessor);
    this.updateAccessDefinitionObjectProcessor = updateAccessDefinitionObjectProcessor;
    this.updateAccessDefinitionObjectResponseProcessor =
        updateAccessDefinitionObjectResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_UPDATE_ACCESS_DEFINITION_OBJECT)
        .request(updateAccessDefinitionObjectProcessor)
        .unmarshal(UpdateAccessDefinitionObjectAnfrageDTO.class)
        .response(updateAccessDefinitionObjectResponseProcessor);
  }
}
