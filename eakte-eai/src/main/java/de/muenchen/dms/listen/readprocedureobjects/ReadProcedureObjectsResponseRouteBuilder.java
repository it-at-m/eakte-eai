package de.muenchen.dms.listen.readprocedureobjects;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_PROCEDURE_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadProcedureObjectsResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadProcedureObjectsProcessor requestProcessor;
  private final ReadProcedureObjectsResponseProcessor responseProcessor;

  public ReadProcedureObjectsResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadProcedureObjectsProcessor requestProcessor,
      ReadProcedureObjectsResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_PROCEDURE_OBJECT).request(requestProcessor).response(responseProcessor);
  }
}
