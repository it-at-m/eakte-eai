package de.muenchen.dms.businessdata.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateBusinessDataValueGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateBusinessDataValueProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    final UpdateBusinessDataValueDTO anfrage =
        exchange.getIn().getBody(UpdateBusinessDataValueDTO.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final UpdateBusinessDataValueGI parameters =
        createUpdateBusinessDataValueGI(
            getNutzer(exchange),
            objaddress,
            anfrage,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateBusinessDataValueGI.class);
  }

  private UpdateBusinessDataValueGI createUpdateBusinessDataValueGI(
      final String userlogin,
      final String objid,
      final UpdateBusinessDataValueDTO anfrage,
      final String jobposition,
      final String joboe,
      final String anwendung) {
    UpdateBusinessDataValueGI updateBusinessDataValueGI = new UpdateBusinessDataValueGI();
    updateBusinessDataValueGI.setUserlogin(userlogin);
    updateBusinessDataValueGI.setObjaddress(objid);
    updateBusinessDataValueGI.setReference(anfrage.getReference());
    updateBusinessDataValueGI.setValue(anfrage.getValue());
    updateBusinessDataValueGI.setJobposition(jobposition);
    updateBusinessDataValueGI.setJoboe(joboe);
    updateBusinessDataValueGI.setBusinessapp(anwendung);
    return updateBusinessDataValueGI;
  }
}
