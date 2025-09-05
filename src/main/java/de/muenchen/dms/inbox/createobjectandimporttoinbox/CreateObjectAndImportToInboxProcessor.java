package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateObjectAndImportToInboxGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import java.util.List;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateObjectAndImportToInboxProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateObjectAndImportToInboxDTO dto =
        exchange.getIn().getBody(CreateObjectAndImportToInboxDTO.class);

    final List<List<String>> filesubj = dto.getFilesubj();

    final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachment =
        createLHMBAI151700GIAttachmentsForCOAITI(createAttachments(exchange), filesubj);

    final CreateObjectAndImportToInboxGI parameters =
        createObjectAndImportToInbox(
            dto,
            getNutzer(exchange),
            getAnwendung(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            lhmbai151700GIAttachment);
    setParameters(exchange, parameters, CreateObjectAndImportToInboxGI.class);
  }

  private CreateObjectAndImportToInboxGI createObjectAndImportToInbox(
      final CreateObjectAndImportToInboxDTO dto,
      final String nutzer,
      final String anwendung,
      final String organisationseinheit,
      final String rolle,
      final ArrayOfLHMBAI151700GIAttachmentType lhmbai151700GIAttachmentType) {
    final CreateObjectAndImportToInboxGI createObjectAndImportToInboxGI =
        objectFactory.createCreateObjectAndImportToInboxGI();
    createObjectAndImportToInboxGI.setObjaddress(dto.getObjaddress());
    createObjectAndImportToInboxGI.setUserlogin(nutzer);
    createObjectAndImportToInboxGI.setBusinessapp(anwendung);
    createObjectAndImportToInboxGI.setJoboe(organisationseinheit);
    createObjectAndImportToInboxGI.setJobposition(rolle);
    createObjectAndImportToInboxGI.setGiattachmenttype(lhmbai151700GIAttachmentType);

    return createObjectAndImportToInboxGI;
  }
}
