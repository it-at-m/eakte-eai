package de.muenchen.dms.akte.readapentry;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadApentryGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadApentryProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final ReadApentryGI parameters =
        readApentryGI(
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadApentryGI.class);
  }

  private ReadApentryGI readApentryGI(
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadApentryGI readApentryGI = objectFactory.createReadApentryGI();
    readApentryGI.setUserlogin(nutzer);
    readApentryGI.setObjaddress(objaddress);
    readApentryGI.setBusinessapp(anwendung);
    readApentryGI.setJoboe(organisationseinheit);
    readApentryGI.setJobposition(rolle);
    return readApentryGI;
  }
}
