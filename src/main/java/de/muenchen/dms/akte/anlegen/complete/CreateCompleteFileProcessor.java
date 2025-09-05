package de.muenchen.dms.akte.anlegen.complete;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteFileGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteFileProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    CreateCompleteFileBodyParams dto = exchange.getIn().getBody(CreateCompleteFileBodyParams.class);

    LHMBAI151700GIAttachmentType attachmentType =
        createLHMBAI151700GIAttachment(createAttachment(exchange));

    CreateCompleteFileGI parameter =
        createCompleteFile(
            getNutzer(exchange),
            dto,
            attachmentType,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameter, CreateCompleteFileGI.class);
  }

  CreateCompleteFileGI createCompleteFile(
      final String userlogin,
      CreateCompleteFileBodyParams dto,
      final LHMBAI151700GIAttachmentType attachmentType,
      final String jobpositon,
      final String joboe,
      final String businessapp) {
    CreateCompleteFileGI createCompleteFileGI = objectFactory.createCreateCompleteFileGI();
    createCompleteFileGI.setUserlogin(userlogin);
    createCompleteFileGI.setApentry(dto.getApentry());
    createCompleteFileGI.setShortnamefile(dto.getShortnamefile());
    createCompleteFileGI.setShortnameprocedure(dto.getShortnameprocedure());
    createCompleteFileGI.setShortnamedocument(dto.getShortnamedocument());
    createCompleteFileGI.setGiattachmenttype(attachmentType);
    createCompleteFileGI.setFileouobj(dto.getFileouobj());
    createCompleteFileGI.setJobposition(jobpositon);
    createCompleteFileGI.setJoboe(joboe);
    createCompleteFileGI.setBusinessapp(businessapp);
    return createCompleteFileGI;
  }
}
