package de.muenchen.dms.addressee.add;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.AddAddressee;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class AddAddresseeProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {

    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final AddAddresseeRequestDTO dto = exchange.getIn().getBody(AddAddresseeRequestDTO.class);

    final AddAddressee parameter =
        addAddressee(
            dto,
            objectId,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameter, AddAddressee.class);
  }

  private AddAddressee addAddressee(
      final AddAddresseeRequestDTO anfrage,
      final String objectId,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung)
      throws DatatypeConfigurationException {
    final AddAddressee addAddressee = objectFactory.createAddAddressee();
    addAddressee.setUserlogin(nutzer);
    addAddressee.setObjaddress(objectId);
    addAddressee.setJoboe(organisationseinheit);
    addAddressee.setJobposition(rolle);
    addAddressee.setBusinessapp(anwendung);
    addAddressee.setAddrdate(JacksonData.toXMLGregorianCalendar(anfrage.getAddrdate()));
    addAddressee.setAddrtransmedia(anfrage.getAddrtransmedia());
    addAddressee.setAddrorgname(anfrage.getAddrorgname());
    addAddressee.setAddrtitle(anfrage.getAddrtitle());
    addAddressee.setAddrfirstname(anfrage.getAddrfirstname());
    addAddressee.setAddrname(anfrage.getAddrname());
    addAddressee.setAddrstreet(anfrage.getAddrstreet());
    addAddressee.setAddrstreetnumber(anfrage.getAddrstreetnumber());
    addAddressee.setAddrzipcode(anfrage.getAddrzipcode());
    addAddressee.setAddrcity(anfrage.getAddrcity());
    addAddressee.setAddrcountry(anfrage.getAddrcountry());
    addAddressee.setAddremail(anfrage.getAddremail());
    addAddressee.setAddradditional5(anfrage.getAddradditional5());
    return addAddressee;
  }
}
