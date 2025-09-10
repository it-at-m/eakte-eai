package de.muenchen.dms.disposalschedule.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetDisposalScheduleDateGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SetDisposalScheduleDateProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final SetDisposalScheduleDateAnfrageDTO anfrage =
        exchange.getIn().getBody(SetDisposalScheduleDateAnfrageDTO.class);
    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final SetDisposalScheduleDateGI parameters =
        createSetDisposalScheduleDateGI(
            getNutzer(exchange),
            objectId,
            anfrage,
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, SetDisposalScheduleDateGI.class);
  }

  private SetDisposalScheduleDateGI createSetDisposalScheduleDateGI(
      final String nutzer,
      final String objectId,
      final SetDisposalScheduleDateAnfrageDTO anfrage,
      final String organisationseinheit,
      final String rolle,
      final String anwendung)
      throws DatatypeConfigurationException {
    SetDisposalScheduleDateGI SetDisposalScheduleDateGI = new SetDisposalScheduleDateGI();
    SetDisposalScheduleDateGI.setUserlogin(nutzer);
    SetDisposalScheduleDateGI.setBusinessapp(anwendung);
    SetDisposalScheduleDateGI.setObjaddress(objectId);
    SetDisposalScheduleDateGI.setJoboe(organisationseinheit);
    SetDisposalScheduleDateGI.setJobposition(rolle);
    SetDisposalScheduleDateGI.setOffltscheduled(
        JacksonData.toXMLGregorianCalendar(anfrage.getOffltscheduled()));

    return SetDisposalScheduleDateGI;
  }
}
