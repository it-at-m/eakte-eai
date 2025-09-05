package de.muenchen.dms.objekt.reassign;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReAssignObjectGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReAssignObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final ReAssignObjectDTO anfrage = exchange.getIn().getBody(ReAssignObjectDTO.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    final ReAssignObjectGI parameters =
        createReassignObjectI(
            anfrage,
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReAssignObjectGI.class);
  }

  private ReAssignObjectGI createReassignObjectI(
      final ReAssignObjectDTO anfrage,
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReAssignObjectGI reAssignObjectGI = objectFactory.createReAssignObjectGI();
    reAssignObjectGI.setUserlogin(nutzer);
    reAssignObjectGI.setBusinessapp(anwendung);
    reAssignObjectGI.setObjaddress(objaddress);
    reAssignObjectGI.setNewtarget(anfrage.getNewtarget());
    reAssignObjectGI.setNewoe(anfrage.getNewoe());
    reAssignObjectGI.setJobposition(rolle);
    reAssignObjectGI.setJoboe(organisationseinheit);

    return reAssignObjectGI;
  }
}
