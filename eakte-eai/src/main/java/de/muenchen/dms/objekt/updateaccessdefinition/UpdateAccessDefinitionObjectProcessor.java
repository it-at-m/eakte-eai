package de.muenchen.dms.objekt.updateaccessdefinition;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateAccessDefinitionObjectGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateAccessDefinitionObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final UpdateAccessDefinitionObjectAnfrageDTO anfrage =
        exchange.getIn().getBody(UpdateAccessDefinitionObjectAnfrageDTO.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    final UpdateAccessDefinitionObjectGI parameters =
        createupdateAccessDefinitionObjectGI(
            getNutzer(exchange),
            objaddress,
            anfrage,
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateAccessDefinitionObjectGI.class);
  }

  private UpdateAccessDefinitionObjectGI createupdateAccessDefinitionObjectGI(
      final String nutzer,
      final String objaddress,
      final UpdateAccessDefinitionObjectAnfrageDTO anfrage,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    UpdateAccessDefinitionObjectGI updateAccessDefinitionObjectGI =
        new UpdateAccessDefinitionObjectGI();
    updateAccessDefinitionObjectGI.setUserlogin(nutzer);
    updateAccessDefinitionObjectGI.setBusinessapp(anwendung);
    updateAccessDefinitionObjectGI.setObjaddress(objaddress);
    updateAccessDefinitionObjectGI.setJoboe(organisationseinheit);
    updateAccessDefinitionObjectGI.setJobposition(rolle);
    updateAccessDefinitionObjectGI.setObjaccdef(anfrage.getObjaccdef());
    updateAccessDefinitionObjectGI.setAuthinheritance(anfrage.getAuthinheritance());
    return updateAccessDefinitionObjectGI;
  }
}
