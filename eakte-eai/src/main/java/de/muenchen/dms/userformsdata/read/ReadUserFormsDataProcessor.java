package de.muenchen.dms.userformsdata.read;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadUserFormsDataGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadUserFormsDataProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {

    final String objaddress =
        exchange.getIn().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    final String reference = exchange.getIn().getHeader("reference", String.class);

    final ReadUserFormsDataRequestDTO dto = new ReadUserFormsDataRequestDTO();
    dto.setReference(reference);

    // Erstellen der Parameter für den nächsten Verarbeitungsschritt
    final ReadUserFormsDataGI parameters =
        readUserFormsDataGI(
            getNutzer(exchange),
            objaddress,
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));

    // Setzen der Parameter im Exchange
    setParameters(exchange, parameters, ReadUserFormsDataGI.class);
  }

  private ReadUserFormsDataGI readUserFormsDataGI(
      final String userlogin,
      final String objaddress,
      final ReadUserFormsDataRequestDTO dto,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    ReadUserFormsDataGI readUserFormsDataGI = objectFactory.createReadUserFormsDataGI();
    readUserFormsDataGI.setUserlogin(userlogin);
    readUserFormsDataGI.setObjaddress(objaddress);
    readUserFormsDataGI.setReference(dto.getReference());
    readUserFormsDataGI.setJobposition(jobposition);
    readUserFormsDataGI.setJoboe(joboe);
    readUserFormsDataGI.setBusinessapp(businessapp);

    return readUserFormsDataGI;
  }
}
