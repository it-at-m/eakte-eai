/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.vorgang.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateProcedureGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateProcedureProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final UpdateProcedureDTO anfrage = exchange.getIn().getBody(UpdateProcedureDTO.class);
    final String procedureaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final UpdateProcedureGI parameters =
        createUpdateProcedureGI(
            anfrage,
            procedureaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateProcedureGI.class);
  }

  private UpdateProcedureGI createUpdateProcedureGI(
      final UpdateProcedureDTO anfrage,
      final String procedureaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung)
      throws DatatypeConfigurationException {
    final UpdateProcedureGI updateProcedureGI = objectFactory.createUpdateProcedureGI();
    updateProcedureGI.setUserlogin(nutzer);
    updateProcedureGI.setBusinessapp(anwendung);
    updateProcedureGI.setObjaddress(procedureaddress);
    updateProcedureGI.setShortname(anfrage.getShortname());
    updateProcedureGI.setFilesubj(anfrage.getFilesubj());
    updateProcedureGI.setAccdef(anfrage.getAccdef());
    updateProcedureGI.setProcremark(anfrage.getProcremark());
    updateProcedureGI.setFiletype(anfrage.getFiletype());
    updateProcedureGI.setObjterms(anfrage.getObjterms());
    updateProcedureGI.setFileruntimefrom(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimefrom()));
    updateProcedureGI.setFileruntimetill(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimetill()));
    updateProcedureGI.setFilearchivetimeOffltdeadline(anfrage.getFilearchivetimeOffltdeadline());
    updateProcedureGI.setFilelifetimeOffltdeadline(anfrage.getFilelifetimeOffltdeadline());
    updateProcedureGI.setOffltdispstate(anfrage.getOffltdispstate());
    updateProcedureGI.setJoboe(organisationseinheit);
    updateProcedureGI.setJobposition(rolle);

    return updateProcedureGI;
  }
}
