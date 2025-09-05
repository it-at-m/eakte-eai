package de.muenchen.dms.schriftstueck.ausgang.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateOutgoingGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateOutgoingProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    UpdateOutgoingRequestDTO dto = exchange.getIn().getBody(UpdateOutgoingRequestDTO.class);

    final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType =
        createLHMBAI151700GIAttachments(createAttachments(exchange));

    final String objectId =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final UpdateOutgoingGI parameter =
        updateOutgoingGI(
            dto,
            objectId,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange),
            lhmbai151700GIAttachmentType);
    setParameters(exchange, parameter, UpdateOutgoingGI.class);
  }

  private UpdateOutgoingGI updateOutgoingGI(
      final UpdateOutgoingRequestDTO anfrage,
      final String objectId,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung,
      final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType)
      throws DatatypeConfigurationException {
    final UpdateOutgoingGI updateOutgoingGI = objectFactory.createUpdateOutgoingGI();
    updateOutgoingGI.setObjaddress(objectId);
    updateOutgoingGI.setUserlogin(nutzer);
    updateOutgoingGI.setJoboe(organisationseinheit);
    updateOutgoingGI.setJobposition(rolle);
    updateOutgoingGI.setBusinessapp(anwendung);
    updateOutgoingGI.setGiattachmenttype(lhmbai151700GIAttachmentType);
    updateOutgoingGI.setShortname(anfrage.getShortname());
    updateOutgoingGI.setAccdef(anfrage.getAccdef());
    updateOutgoingGI.setReferredincoming(anfrage.getReferredincoming());
    updateOutgoingGI.setFilesubj(anfrage.getFilesubj());
    updateOutgoingGI.setOutgoingdate(JacksonData.toXMLGregorianCalendar(anfrage.getOutgoingdate()));
    updateOutgoingGI.setIncattachments(anfrage.getIncattachments());
    updateOutgoingGI.setObjterms(anfrage.getObjterms());
    return updateOutgoingGI;
  }
}
