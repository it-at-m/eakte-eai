/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2025
 */
package de.muenchen.dms.aktenplaneintrag.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateSubjectAreaGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateSubjectAreaProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final UpdateSubjectAreaRequestDTO anfrage =
        exchange.getIn().getBody(UpdateSubjectAreaRequestDTO.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final UpdateSubjectAreaGI parameters =
        createUpdateSubjectAreaGI(
            anfrage,
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateSubjectAreaGI.class);
  }

  private UpdateSubjectAreaGI createUpdateSubjectAreaGI(
      final UpdateSubjectAreaRequestDTO anfrage,
      final String procedureaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung)
      throws DatatypeConfigurationException {
    final UpdateSubjectAreaGI updateSubjectAreaGI = objectFactory.createUpdateSubjectAreaGI();
    updateSubjectAreaGI.setUserlogin(nutzer);
    updateSubjectAreaGI.setObjaddress(procedureaddress);
    updateSubjectAreaGI.setSubjareascope(anfrage.getSubjareascope());
    updateSubjectAreaGI.setJoboe(organisationseinheit);
    updateSubjectAreaGI.setJobposition(rolle);
    updateSubjectAreaGI.setBusinessapp(anwendung);

    return updateSubjectAreaGI;
  }
}
