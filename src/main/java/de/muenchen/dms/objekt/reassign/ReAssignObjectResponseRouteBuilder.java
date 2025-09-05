package de.muenchen.dms.objekt.reassign;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReAssignObjectResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final ReAssignObjectProcessor reAssignObjectProcessor;
  private final ReAssignObjectResponseProcessor reAssignObjectResponseProcessor;

  public ReAssignObjectResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReAssignObjectProcessor reAssignObjectProcessor,
      ReAssignObjectResponseProcessor reAssignObjectResponseProcessor) {
    super(authorizationProcessor);
    this.reAssignObjectProcessor = reAssignObjectProcessor;
    this.reAssignObjectResponseProcessor = reAssignObjectResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_REASSIGN_OBJECT)
        .request(reAssignObjectProcessor)
        .unmarshal(ReAssignObjectDTO.class)
        .response(reAssignObjectResponseProcessor);
  }
}
