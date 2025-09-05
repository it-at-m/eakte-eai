package de.muenchen.dms.schriftstueck.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateContentObjectGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import java.util.List;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateContentObjectProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    CreateContentObjectAnfrageDTO dto =
        exchange.getIn().getBody(CreateContentObjectAnfrageDTO.class);

    final List<String> filesubj = dto.getFilesubj();

    final LHMBAI151700GIAttachmentType lhmbai151700GIAttachment =
        createLHMBAI151700GIAttachmentWithFilesubj(createAttachment(exchange), filesubj);
    final CreateContentObjectGI parameters =
        createCreateContentObjectGI(
            dto,
            getNutzer(exchange),
            lhmbai151700GIAttachment,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, CreateContentObjectGI.class);
  }

  private CreateContentObjectGI createCreateContentObjectGI(
      final CreateContentObjectAnfrageDTO anfrage,
      final String nutzer,
      final LHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType,
      final String rolle,
      final String organisationseinheit,
      final String anwendung) {
    final CreateContentObjectGI createContentObjectGI = objectFactory.createCreateContentObjectGI();
    createContentObjectGI.setUserlogin(nutzer);
    createContentObjectGI.setReferrednumber(anfrage.getReferrednumber());
    createContentObjectGI.setGiattachmenttype(lhmbai151700GIAttachmentType);
    createContentObjectGI.setJobposition(rolle);
    createContentObjectGI.setJoboe(organisationseinheit);
    createContentObjectGI.setBusinessapp(anwendung);

    return createContentObjectGI;
  }
}
