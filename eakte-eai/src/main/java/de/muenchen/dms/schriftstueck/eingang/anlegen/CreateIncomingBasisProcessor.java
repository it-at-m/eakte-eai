/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIUserFormsType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;

import de.muenchen.dms.common.util.Umwandlungen;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingBasisProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange) throws Exception {
    CreateIncomingBasisAnfrageDTO dto = getMessageBody(exchange);

    ArrayOfLHMBAI151700GIUserFormsType userFormsType =
        Umwandlungen.wandleUserFormsGIZuUserFormsReferenz(dto.getUserformsdata());

    final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachments =
        createLHMBAI151700GIAttachments((createAttachments(exchange)));

    final CreateIncomingGI parameters =
        createIncomingGI(
            dto,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange),
            lhmbai151700GIAttachments,
            userFormsType);
    setParameters(exchange, parameters, CreateIncomingGI.class);
  }

  private CreateIncomingBasisAnfrageDTO getMessageBody(Exchange exchange) {
    return exchange.getIn().getBody(CreateIncomingBasisAnfrageDTO.class);
  }

  private CreateIncomingGI createIncomingGI(
      final CreateIncomingBasisAnfrageDTO anfrage,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung,
      final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachments,
      final ArrayOfLHMBAI151700GIUserFormsType userFormsType)
      throws DatatypeConfigurationException {
    final CreateIncomingGI createIncomingGI = objectFactory.createCreateIncomingGI();
    createIncomingGI.setUserlogin(nutzer);
    createIncomingGI.setReferrednumber(anfrage.getReferrednumber());
    createIncomingGI.setUseou(anfrage.getUseou());
    createIncomingGI.setDelivery(JacksonData.toXMLGregorianCalendar(anfrage.getDelivery()));
    createIncomingGI.setBusinessapp(anwendung);
    createIncomingGI.setShortname(anfrage.getShortname());
    createIncomingGI.setFilesubj(anfrage.getFilesubj());
    createIncomingGI.setAccdef(anfrage.getAccdef());
    createIncomingGI.setForeignnr(anfrage.getForeignnr());
    createIncomingGI.setDocumentremarks(anfrage.getDocumentremarks());
    createIncomingGI.setIncattachments(anfrage.getIncattachments());
    createIncomingGI.setObjterms(anfrage.getObjterms());
    createIncomingGI.setGiattachmenttype(lhmbai151700GIAttachments);
    createIncomingGI.setDefinition(anfrage.getDefinition());
    createIncomingGI.setUserformsdata(userFormsType);
    createIncomingGI.setJoboe(organisationseinheit);
    createIncomingGI.setJobposition(rolle);
    return createIncomingGI;
  }
}
