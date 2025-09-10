package de.muenchen.dms.addressee.get;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.GetAddressee;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class GetAddresseeProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final GetAddressee parameters =
        createGetAddressee(
            objaddress,
            getNutzer(exchange),
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, GetAddressee.class);
  }

  private GetAddressee createGetAddressee(
      final String objaddress,
      final String userlogin,
      final String jobposition,
      final String joboe,
      final String anwendung) {
    final GetAddressee getAddressee = new GetAddressee();
    getAddressee.setObjaddress(objaddress);
    getAddressee.setUserlogin(userlogin);
    getAddressee.setJobposition(jobposition);
    getAddressee.setJoboe(joboe);
    getAddressee.setBusinessapp(anwendung);
    return getAddressee;
  }
}
