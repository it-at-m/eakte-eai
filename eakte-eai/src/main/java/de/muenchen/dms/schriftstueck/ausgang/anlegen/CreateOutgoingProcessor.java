/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateOutgoingGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

/**
 * Diese Klasse verarbeitet eine Anfrage von einem REST-Endpunkt. Die Daten werden dann in ein
 * SOAP-Nachrichten-Objekt umgewandelt.
 */
@Component
public class CreateOutgoingProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateOutgoingAnfrageDTO dto = getMessageBody(exchange);

    final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachments =
        createLHMBAI151700GIAttachments((createAttachments(exchange)));
    // Erzeuge eine SOAP-Nachricht
    final CreateOutgoingGI parameters =
        createOutgoingGI(
            dto,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange),
            lhmbai151700GIAttachments);
    setParameters(exchange, parameters, CreateOutgoingGI.class);
  }

  private CreateOutgoingAnfrageDTO getMessageBody(Exchange exchange) {
    return exchange.getIn().getBody(CreateOutgoingAnfrageDTO.class);
  }

  private CreateOutgoingGI createOutgoingGI(
      final CreateOutgoingAnfrageDTO anfrage,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung,
      final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachments)
      throws DatatypeConfigurationException {
    final CreateOutgoingGI createOutgoingGI = objectFactory.createCreateOutgoingGI();
    createOutgoingGI.setUserlogin(nutzer);
    createOutgoingGI.setBusinessapp(anwendung);
    createOutgoingGI.setReferrednumber(anfrage.getReferrednumber());
    createOutgoingGI.setReferredincoming(anfrage.getReferredincoming());
    createOutgoingGI.setSubfiletype(anfrage.getSubfiletype());
    createOutgoingGI.setDoctemplate(anfrage.getDoctemplate());
    createOutgoingGI.setShortname(anfrage.getShortname());
    createOutgoingGI.setFilesubj(anfrage.getFilesubj());
    createOutgoingGI.setAccdef(anfrage.getAccdef());
    createOutgoingGI.setIncattachments(anfrage.getIncattachments());
    createOutgoingGI.setObjterms(anfrage.getObjterms());
    createOutgoingGI.setOutgoingdate(JacksonData.toXMLGregorianCalendar(anfrage.getOutgoingdate()));
    createOutgoingGI.setGiattachmenttype(lhmbai151700GIAttachments);
    createOutgoingGI.setJoboe(organisationseinheit);
    createOutgoingGI.setJobposition(rolle);
    return createOutgoingGI;
  }
}
