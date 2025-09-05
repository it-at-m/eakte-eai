/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.vorgang.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadProcedureProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final ReadProcedureGI parameters =
        createReadProcedureGI(
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadProcedureGI.class);
  }

  private ReadProcedureGI createReadProcedureGI(
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadProcedureGI readProcedureGI = objectFactory.createReadProcedureGI();
    readProcedureGI.setUserlogin(nutzer);
    readProcedureGI.setBusinessapp(anwendung);
    readProcedureGI.setObjaddress(objaddress);
    readProcedureGI.setJoboe(organisationseinheit);
    readProcedureGI.setJobposition(rolle);
    return readProcedureGI;
  }
}
