package de.muenchen.dms.signature.set;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SetSignatureObjectGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SetSignatureProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final SetSignatureRequestDTO anfrage = exchange.getIn().getBody(SetSignatureRequestDTO.class);

    final SetSignatureObjectGI parameter =
        setSignature(
            anfrage,
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameter, SetSignatureObjectGI.class);
  }

  private SetSignatureObjectGI setSignature(
      final SetSignatureRequestDTO anfrage,
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final SetSignatureObjectGI setSignature = objectFactory.createSetSignatureObjectGI();
    setSignature.setUserlogin(nutzer);
    setSignature.setObjaddress(objaddress);
    setSignature.setSignaturetype(anfrage.getSignaturetype());
    setSignature.setRemark(anfrage.getRemark());
    setSignature.setJobposition(rolle);
    setSignature.setJoboe(organisationseinheit);
    setSignature.setBusinessapp(anwendung);
    return setSignature;
  }
}
