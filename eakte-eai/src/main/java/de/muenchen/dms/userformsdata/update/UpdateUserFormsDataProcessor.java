package de.muenchen.dms.userformsdata.update;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIUserFormsType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateUserFormsDataGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.util.Umwandlungen;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class UpdateUserFormsDataProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final UpdateUserFormsDataRequestDTO dto =
        exchange.getIn().getBody(UpdateUserFormsDataRequestDTO.class);

    ArrayOfLHMBAI151700GIUserFormsType userFormsType =
        Umwandlungen.wandleUserFormsGIZuUserFormsReferenz(dto.getUserformsdata());
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final UpdateUserFormsDataGI parameters =
        createUpdateUserFormsDataGi(
            getNutzer(exchange),
            objaddress,
            userFormsType,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, UpdateUserFormsDataGI.class);
  }

  private UpdateUserFormsDataGI createUpdateUserFormsDataGi(
      final String userlogin,
      final String objaddress,
      final ArrayOfLHMBAI151700GIUserFormsType userFormsType,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    final UpdateUserFormsDataGI updateUserFormsDataGI = objectFactory.createUpdateUserFormsDataGI();
    updateUserFormsDataGI.setUserlogin(userlogin);
    updateUserFormsDataGI.setObjaddress(objaddress);
    updateUserFormsDataGI.setUserformsdata(userFormsType);
    updateUserFormsDataGI.setJobposition(jobposition);
    updateUserFormsDataGI.setJoboe(joboe);
    updateUserFormsDataGI.setBusinessapp(businessapp);

    return updateUserFormsDataGI;
  }
}
