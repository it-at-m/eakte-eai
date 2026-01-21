package de.muenchen.dms.schriftstueck.aendern;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateContentObjectGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import java.util.List;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateContentObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    UpdateContentObjectDTO dto = exchange.getIn().getBody(UpdateContentObjectDTO.class);

    if (dto != null) {
      final List<String> filesubj = dto.getFilesubj();

      final LHMBAI151700GIAttachmentType attachment =
          createLHMBAI151700GIAttachmentWithFilesubj(createAttachment(exchange), filesubj);

      final String objectId =
          exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

      final UpdateContentObjectGI parameter =
          createUpdateContentObjectGI(
              objectId,
              getNutzer(exchange),
              getRolle(exchange),
              getOrganisationseinheit(exchange),
              getAnwendung(exchange),
              attachment);
      setParameters(exchange, parameter, UpdateContentObjectGI.class);
    } else {
      final LHMBAI151700GIAttachmentType attachment =
          createLHMBAI151700GIAttachment(createAttachment(exchange));

      final String objectId =
          exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

      final UpdateContentObjectGI parameter =
          createUpdateContentObjectGI(
              objectId,
              getNutzer(exchange),
              getRolle(exchange),
              getOrganisationseinheit(exchange),
              getAnwendung(exchange),
              attachment);
      setParameters(exchange, parameter, UpdateContentObjectGI.class);
    }
  }

  private UpdateContentObjectGI createUpdateContentObjectGI(
      final String objectId,
      final String nutzer,
      final String rolle,
      final String organisationseinheit,
      final String anwendung,
      final LHMBAI151700GIAttachmentType attachment) {
    final UpdateContentObjectGI updateContentObjectGI = new UpdateContentObjectGI();
    updateContentObjectGI.setObjaddress(objectId);
    updateContentObjectGI.setUserlogin(nutzer);
    updateContentObjectGI.setJobposition(rolle);
    updateContentObjectGI.setJoboe(organisationseinheit);
    updateContentObjectGI.setBusinessapp(anwendung);
    updateContentObjectGI.setGiattachmenttype(attachment);
    return updateContentObjectGI;
  }
}
