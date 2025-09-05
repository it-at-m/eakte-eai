package de.muenchen.dms.schriftstueck.ausgang.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadOutgoingGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadOutgoingProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Erzeuge eine SOAP-Nachricht
    final ReadOutgoingGI parameters =
        readOutgoingGI(
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadOutgoingGI.class);
  }

  private ReadOutgoingGI readOutgoingGI(
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadOutgoingGI readOutgoingGI = objectFactory.createReadOutgoingGI();
    readOutgoingGI.setUserlogin(nutzer);
    readOutgoingGI.setObjaddress(objaddress);
    readOutgoingGI.setBusinessapp(anwendung);
    readOutgoingGI.setJoboe(organisationseinheit);
    readOutgoingGI.setJobposition(rolle);
    return readOutgoingGI;
  }
}
