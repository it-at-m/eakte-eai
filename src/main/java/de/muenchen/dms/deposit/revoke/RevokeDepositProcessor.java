package de.muenchen.dms.deposit.revoke;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.RevokeDepositObjectGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class RevokeDepositProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final RevokeDepositObjectGI parameters =
        createRevokeDeposit(
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, RevokeDepositObjectGI.class);
  }

  private RevokeDepositObjectGI createRevokeDeposit(
      final String objid,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final RevokeDepositObjectGI revokeDepositObjectGI = objectFactory.createRevokeDepositObjectGI();
    revokeDepositObjectGI.setUserlogin(nutzer);
    revokeDepositObjectGI.setBusinessapp(anwendung);
    revokeDepositObjectGI.setJobposition(organisationseinheit);
    revokeDepositObjectGI.setJobposition(rolle);
    revokeDepositObjectGI.setObjaddress(objid);
    return revokeDepositObjectGI;
  }
}
