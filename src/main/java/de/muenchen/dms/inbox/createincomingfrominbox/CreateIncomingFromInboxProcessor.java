package de.muenchen.dms.inbox.createincomingfrominbox;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingFromInboxGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingFromInboxProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CreateIncomingFromInboxRequestDTO dto =
        exchange.getIn().getBody(CreateIncomingFromInboxRequestDTO.class);

    final CreateIncomingFromInboxGI parameters =
        createIncomingFromInbox(
            getNutzer(exchange),
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, CreateIncomingFromInboxGI.class);
  }

  private CreateIncomingFromInboxGI createIncomingFromInbox(
      final String userlogin,
      final CreateIncomingFromInboxRequestDTO dto,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    final CreateIncomingFromInboxGI createIncomingFromInboxGI =
        objectFactory.createCreateIncomingFromInboxGI();
    createIncomingFromInboxGI.setUserlogin(userlogin);
    createIncomingFromInboxGI.setInboxid(dto.getInboxid());
    createIncomingFromInboxGI.setReferrednumber(dto.getReferrednumber());
    createIncomingFromInboxGI.setContentid(dto.getContentid());
    createIncomingFromInboxGI.setShortname(dto.getShortname());
    createIncomingFromInboxGI.setForeignnr(dto.getForeignnr());
    createIncomingFromInboxGI.setFilesubj(dto.getFilesubj());
    createIncomingFromInboxGI.setDocumentremarks(dto.getDocumentremarks());
    createIncomingFromInboxGI.setIncattachments(dto.getIncattachments());
    createIncomingFromInboxGI.setObjterms(dto.getObjterms());
    createIncomingFromInboxGI.setJobposition(jobposition);
    createIncomingFromInboxGI.setJoboe(joboe);
    createIncomingFromInboxGI.setBusinessapp(businessapp);

    return createIncomingFromInboxGI;
  }
}
