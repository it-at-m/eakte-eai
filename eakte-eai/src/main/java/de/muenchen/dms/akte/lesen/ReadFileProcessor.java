/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.akte.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadFileGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadFileProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final String aktencooid =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final ReadFileGI parameters =
        createReadFileGI(
            aktencooid,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadFileGI.class);
  }

  private ReadFileGI createReadFileGI(
      final String aktencooid,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadFileGI readFileGI = objectFactory.createReadFileGI();
    readFileGI.setUserlogin(nutzer);
    readFileGI.setBusinessapp(anwendung);
    readFileGI.setJoboe(organisationseinheit);
    readFileGI.setJobposition(rolle);
    readFileGI.setObjaddress(aktencooid);
    return readFileGI;
  }
}
