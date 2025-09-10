package de.muenchen.dms.startworkflow;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class StartWorkflowResponseRouteBuilder extends DmsResponseRouteBuilder {

  StartWorkflowProcessor startWorkflowProcessor;
  StartWorkflowResponseProcessor startWorkflowResponseProcessor;

  public StartWorkflowResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      StartWorkflowProcessor startWorkflowProcessor,
      StartWorkflowResponseProcessor startWorkflowResponseProcessor) {
    super(authorizationProcessor);
    this.startWorkflowProcessor = startWorkflowProcessor;
    this.startWorkflowResponseProcessor = startWorkflowResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_START_WORKFLOW)
        .request(startWorkflowProcessor)
        .unmarshal(StartWorkflowDTO.class)
        .response(startWorkflowResponseProcessor);
  }
}
