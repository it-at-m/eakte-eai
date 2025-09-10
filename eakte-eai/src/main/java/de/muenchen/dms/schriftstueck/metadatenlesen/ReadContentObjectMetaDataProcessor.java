package de.muenchen.dms.schriftstueck.metadatenlesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectMetaDataGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectMetaDataProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Erzeuge eine SOAP-Nachricht
    final ReadContentObjectMetaDataGI parameters =
        readContentObjectMetaDataGI(
            objaddress,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadContentObjectMetaDataGI.class);
  }

  private ReadContentObjectMetaDataGI readContentObjectMetaDataGI(
      final String objaddress,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadContentObjectMetaDataGI readContentObjectMetaDataGI =
        objectFactory.createReadContentObjectMetaDataGI();
    readContentObjectMetaDataGI.setUserlogin(nutzer);
    readContentObjectMetaDataGI.setObjaddress(objaddress);
    readContentObjectMetaDataGI.setBusinessapp(anwendung);
    readContentObjectMetaDataGI.setJoboe(organisationseinheit);
    readContentObjectMetaDataGI.setJobposition(rolle);
    return readContentObjectMetaDataGI;
  }
}
