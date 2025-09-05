package de.muenchen.dms.startworkflow;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.StartWorkflowGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class StartWorkflowProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final StartWorkflowDTO dto = exchange.getIn().getBody(StartWorkflowDTO.class);

    StartWorkflowGI parameters =
        startWorkflowGI(
            getNutzer(exchange),
            objaddress,
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));

    setParameters(exchange, parameters, StartWorkflowGI.class);
  }

  StartWorkflowGI startWorkflowGI(
      String userlogin,
      String objaddress,
      StartWorkflowDTO dto,
      String jobposition,
      String joboe,
      String businessapp) {
    StartWorkflowGI startWorkflowGI = objectFactory.createStartWorkflowGI();
    startWorkflowGI.setUserlogin(userlogin);
    startWorkflowGI.setObjaddress(objaddress);
    startWorkflowGI.setWorkflowpattern(dto.getWorkflowpattern());
    startWorkflowGI.setJobposition(jobposition);
    startWorkflowGI.setJoboe(joboe);
    startWorkflowGI.setBusinessapp(businessapp);

    return startWorkflowGI;
  }
}
