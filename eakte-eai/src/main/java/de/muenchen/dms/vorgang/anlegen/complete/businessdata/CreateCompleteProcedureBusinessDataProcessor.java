package de.muenchen.dms.vorgang.anlegen.complete.businessdata;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureBusinessDataGI;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateCompleteProcedureBusinessDataProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final LHMBAI151700GIAttachmentType giAttachmentType =
        createLHMBAI151700GIAttachment(createAttachment(exchange));

    final CreateCompleteProcedureBusinessDataBodyParams dto =
        exchange.getIn().getBody(CreateCompleteProcedureBusinessDataBodyParams.class);

    CreateCompleteProcedureBusinessDataGI parameter =
        createCompleteProcedureBusinessData(
            getNutzer(exchange),
            dto,
            giAttachmentType,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameter, CreateCompleteProcedureBusinessDataGI.class);
  }

  private CreateCompleteProcedureBusinessDataGI createCompleteProcedureBusinessData(
      String userlogin,
      CreateCompleteProcedureBusinessDataBodyParams dto,
      LHMBAI151700GIAttachmentType giAttachmentType,
      String jobposition,
      String joboe,
      String businessapp) {
    CreateCompleteProcedureBusinessDataGI gi =
        objectFactory.createCreateCompleteProcedureBusinessDataGI();
    gi.setUserlogin(userlogin);
    gi.setBusinessterm(dto.getBusinessterm());
    gi.setBusinessvalue(dto.getBusinessvalue());
    gi.setShortnameprocedure(dto.getShortnameprocedure());
    gi.setShortnamedocument(dto.getShortnamedocument());
    gi.setGiattachmenttype(giAttachmentType);
    gi.setJobposition(jobposition);
    gi.setJoboe(joboe);
    gi.setBusinessapp(businessapp);

    return gi;
  }
}
