/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.akte.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateFileGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateFileProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final UpdateFileDTO anfrage = exchange.getIn().getBody(UpdateFileDTO.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final UpdateFileGI parameters =
        createUpdateFileGI(
            anfrage,
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateFileGI.class);
  }

  private UpdateFileGI createUpdateFileGI(
      final UpdateFileDTO anfrage,
      final String aktenid,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung)
      throws DatatypeConfigurationException {
    final UpdateFileGI updateFileGI = objectFactory.createUpdateFileGI();
    updateFileGI.setUserlogin(nutzer);
    updateFileGI.setBusinessapp(anwendung);
    updateFileGI.setObjaddress(aktenid);
    updateFileGI.setShortname(anfrage.getShortname());
    updateFileGI.setFilesubj(anfrage.getFilesubj());
    updateFileGI.setObjterms(anfrage.getObjterms());
    updateFileGI.setProcedureaccdef(anfrage.getProcedureaccdef());
    updateFileGI.setFileruntimefrom(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimefrom()));
    updateFileGI.setFileruntimetill(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimetill()));
    updateFileGI.setFilearchivetimeOffltdeadline(anfrage.getFilearchivetimeOffltdeadline());
    updateFileGI.setFilelifetimeOffltdeadline(anfrage.getFilelifetimeOffltdeadline());
    updateFileGI.setOffltdispstate(anfrage.getOffltdispstate());
    updateFileGI.setJoboe(organisationseinheit);
    updateFileGI.setJobposition(rolle);

    return updateFileGI;
  }
}
