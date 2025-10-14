package de.muenchen.dms.schriftstueck.internal.create;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIUserFormsType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateInternalGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;

import de.muenchen.dms.common.util.Umwandlungen;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateInternalProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange) throws Exception {
    CreateInternalBodyParams dto = exchange.getIn().getBody(CreateInternalBodyParams.class);

    ArrayOfLHMBAI151700GIUserFormsType userFormsType =
        Umwandlungen.wandleUserFormsGIZuUserFormsReferenz(dto.getUserformsdata());

    final ArrayOfLHMBAI151700GIAttachmentType giAttachmentType =
        createLHMBAI151700GIAttachments(createAttachments(exchange));

    CreateInternalGI parameters =
        createInternalGI(
            dto,
            getNutzer(exchange),
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange),
            giAttachmentType,
            userFormsType);
    setParameters(exchange, parameters, CreateInternalGI.class);
  }

  private CreateInternalGI createInternalGI(
      final CreateInternalBodyParams anfrage,
      final String userlogin,
      final String jobposition,
      final String joboe,
      final String businessapp,
      final ArrayOfLHMBAI151700GIAttachmentType giAttachmentType,
      final ArrayOfLHMBAI151700GIUserFormsType userFormsType)
      throws DatatypeConfigurationException {
    CreateInternalGI createInternalGI = objectFactory.createCreateInternalGI();
    createInternalGI.setUserlogin(userlogin);
    createInternalGI.setJobposition(jobposition);
    createInternalGI.setJoboe(joboe);
    createInternalGI.setBusinessapp(businessapp);
    createInternalGI.setReferrednumber(anfrage.getReferrednumber());
    createInternalGI.setShortname(anfrage.getShortname());
    createInternalGI.setAccdef(anfrage.getAccdef());
    createInternalGI.setDeliverydate(JacksonData.toXMLGregorianCalendar(anfrage.getDeliverydate()));
    createInternalGI.setFilesubj(anfrage.getFilesubj());
    createInternalGI.setSubfiletype(anfrage.getSubfiletype());
    createInternalGI.setDoctemplate(anfrage.getDoctemplate());
    createInternalGI.setIncattachments(anfrage.getIncattachments());
    createInternalGI.setObjterms(anfrage.getObjterms());
    createInternalGI.setGiattachmenttype(giAttachmentType);
    createInternalGI.setDefinition(anfrage.getDefinition());
    createInternalGI.setUserformsdata(userFormsType);
    return createInternalGI;
  }
}
