package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureFromTemplateResponseRouteBuilder extends DmsResponseRouteBuilder {

  CreateProcedureFromTemplateProcessor createProcedureFromTemplateProcessor;
  CreateProcedureFromTemplateResponseProcessor createProcedureFromTemplateResponseProcessor;

  public CreateProcedureFromTemplateResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateProcedureFromTemplateProcessor createProcedureFromTemplateProcessor,
      CreateProcedureFromTemplateResponseProcessor createProcedureFromTemplateResponseProcessor) {
    super(authorizationProcessor);
    this.createProcedureFromTemplateProcessor = createProcedureFromTemplateProcessor;
    this.createProcedureFromTemplateResponseProcessor =
        createProcedureFromTemplateResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_CREATE_PROCEDURE_FROM_TEMPLATE)
        .request(createProcedureFromTemplateProcessor)
        .unmarshal(CreateProcedureFromTemplateRequestDTO.class)
        .response(createProcedureFromTemplateResponseProcessor);
  }
}
