package de.muenchen.dms.schriftstueck.eingang.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateIncomingGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateIncomingProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    UpdateIncomingAnfrageDTO dto = exchange.getIn().getBody(UpdateIncomingAnfrageDTO.class);

    final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType =
        createLHMBAI151700GIAttachments(createAttachments(exchange));

    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final UpdateIncomingGI parameter =
        updateIncomingGI(
            dto,
            objectId,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange),
            lhmbai151700GIAttachmentType);
    setParameters(exchange, parameter, UpdateIncomingGI.class);
  }

  private UpdateIncomingGI updateIncomingGI(
      final UpdateIncomingAnfrageDTO anfrage,
      final String objectId,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung,
      final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType)
      throws DatatypeConfigurationException {
    final UpdateIncomingGI updateOutgoingGI = objectFactory.createUpdateIncomingGI();
    updateOutgoingGI.setObjaddress(objectId);
    updateOutgoingGI.setUserlogin(nutzer);
    updateOutgoingGI.setJobposition(rolle);
    updateOutgoingGI.setJoboe(organisationseinheit);
    updateOutgoingGI.setBusinessapp(anwendung);
    updateOutgoingGI.setGiattachmenttype(lhmbai151700GIAttachmentType);
    updateOutgoingGI.setShortname(anfrage.getShortname());
    updateOutgoingGI.setAccdef(anfrage.getAccdef());
    updateOutgoingGI.setForeignnr(anfrage.getForeignnr());
    updateOutgoingGI.setFilesubj(anfrage.getFilesubj());
    updateOutgoingGI.setDelivery(JacksonData.toXMLGregorianCalendar(anfrage.getDelivery()));
    updateOutgoingGI.setDocumentremarks(anfrage.getDocumentremarks());
    updateOutgoingGI.setIncattachments(anfrage.getIncattachments());
    updateOutgoingGI.setObjterms(anfrage.getObjterms());
    return updateOutgoingGI;
  }
}
