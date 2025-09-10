package de.muenchen.dms.vorgang.anlegen.complete;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    final LHMBAI151700GIAttachmentType giattachmenttype =
        createLHMBAI151700GIAttachment(createAttachment(exchange));

    CreateCompleteProcedureBodyParams dto =
        exchange.getIn().getBody(CreateCompleteProcedureBodyParams.class);

    CreateCompleteProcedureGI parameter =
        createCompleteProcedure(
            getNutzer(exchange),
            dto,
            giattachmenttype,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameter, CreateCompleteProcedureGI.class);
  }

  private CreateCompleteProcedureGI createCompleteProcedure(
      final String userlogin,
      final CreateCompleteProcedureBodyParams dto,
      final LHMBAI151700GIAttachmentType giattachmenttype,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    CreateCompleteProcedureGI createCompleteProcedure =
        objectFactory.createCreateCompleteProcedureGI();
    createCompleteProcedure.setUserlogin(userlogin);
    createCompleteProcedure.setFile(dto.getFile());
    createCompleteProcedure.setShortnameprocedure(dto.getShortnameprocedure());
    createCompleteProcedure.setShortnamedocument(dto.getShortnamedocument());
    createCompleteProcedure.setGiattachmenttype(giattachmenttype);
    createCompleteProcedure.setJobposition(jobposition);
    createCompleteProcedure.setJoboe(joboe);
    createCompleteProcedure.setBusinessapp(businessapp);
    return createCompleteProcedure;
  }
}
