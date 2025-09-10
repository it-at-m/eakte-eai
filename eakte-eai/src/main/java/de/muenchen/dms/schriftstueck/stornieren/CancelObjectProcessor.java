package de.muenchen.dms.schriftstueck.stornieren;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CancelObjectGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CancelObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    var parameters =
        createCancelObjectGI(
            objectId,
            getNutzer(exchange),
            getAnwendung(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange));

    setParameters(exchange, parameters, CancelObjectGI.class);
  }

  private CancelObjectGI createCancelObjectGI(
      final String objectId,
      final String nutzer,
      final String anwendung,
      final String organisationseinheit,
      final String rolle) {
    final CancelObjectGI cancelObjectGI = objectFactory.createCancelObjectGI();
    cancelObjectGI.setObjaddress(objectId);
    cancelObjectGI.setUserlogin(nutzer);
    cancelObjectGI.setBusinessapp(anwendung);
    cancelObjectGI.setJoboe(organisationseinheit);
    cancelObjectGI.setJobposition(rolle);
    return cancelObjectGI;
  }
}
