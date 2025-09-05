package de.muenchen.dms.deposit;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.DepositObjectGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class DepositProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws DatatypeConfigurationException, JsonProcessingException {

    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final DepositObjectGI parameters =
        createDepositGI(
            objaddress,
            getAnwendung(exchange),
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange));
    setParameters(exchange, parameters, DepositObjectGI.class);
  }

  private DepositObjectGI createDepositGI(
      final String objid,
      final String anwendung,
      final String nutzer,
      final String organisationseinheit,
      final String rolle) {
    DepositObjectGI response = new DepositObjectGI();
    response.setBusinessapp(anwendung);
    response.setUserlogin(nutzer);
    response.setObjaddress(objid);
    response.setJobposition(rolle);
    response.setJoboe(organisationseinheit);

    return response;
  }
}
