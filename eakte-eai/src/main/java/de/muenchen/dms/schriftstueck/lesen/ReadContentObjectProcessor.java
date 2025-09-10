/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    var parameters =
        createReadContentObjectGI(
            objectId,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));

    setParameters(exchange, parameters, ReadContentObjectGI.class);
  }

  private ReadContentObjectGI createReadContentObjectGI(
      final String objectId,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadContentObjectGI readContentObjectGI = objectFactory.createReadContentObjectGI();
    readContentObjectGI.setUserlogin(nutzer);
    readContentObjectGI.setBusinessapp(anwendung);
    readContentObjectGI.setObjaddress(objectId);
    readContentObjectGI.setJoboe(organisationseinheit);
    readContentObjectGI.setJobposition(rolle);

    return readContentObjectGI;
  }
}
