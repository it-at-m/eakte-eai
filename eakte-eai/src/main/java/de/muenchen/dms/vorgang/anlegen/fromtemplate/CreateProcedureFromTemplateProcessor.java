package de.muenchen.dms.vorgang.anlegen.fromtemplate;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureFromTemplateGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureFromTemplateProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateProcedureFromTemplateRequestDTO dto =
        exchange.getIn().getBody(CreateProcedureFromTemplateRequestDTO.class);

    CreateProcedureFromTemplateGI parameter =
        createProcedureFromTemplate(
            getNutzer(exchange),
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));

    setParameters(exchange, parameter, CreateProcedureFromTemplateGI.class);
  }

  CreateProcedureFromTemplateGI createProcedureFromTemplate(
      String userlogin,
      CreateProcedureFromTemplateRequestDTO dto,
      String jobposition,
      String joboe,
      String businessapp) {
    CreateProcedureFromTemplateGI createProcedureFromTemplateGI =
        objectFactory.createCreateProcedureFromTemplateGI();
    createProcedureFromTemplateGI.setUserlogin(userlogin);
    createProcedureFromTemplateGI.setReferrednumber(dto.getReferrednumber());
    createProcedureFromTemplateGI.setTemplate(dto.getTemplate());
    createProcedureFromTemplateGI.setJobposition(jobposition);
    createProcedureFromTemplateGI.setJoboe(joboe);
    createProcedureFromTemplateGI.setBusinessapp(businessapp);
    return createProcedureFromTemplateGI;
  }
}
