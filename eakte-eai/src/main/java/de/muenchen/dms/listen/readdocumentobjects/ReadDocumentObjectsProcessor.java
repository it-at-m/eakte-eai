package de.muenchen.dms.listen.readdocumentobjects;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadDocumentGIObjects;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadDocumentObjectsProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) {
    final Integer docstate = exchange.getProperty(QueryProperties.PROPERTY_DOCSTATE, Integer.class);
    final String bostate = exchange.getProperty(QueryProperties.PROPERTY_BOSTATE, String.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final ReadDocumentGIObjects parameters =
        readDocumentGIObjects(
            objaddress,
            docstate,
            bostate,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadDocumentGIObjects.class);
  }

  private ReadDocumentGIObjects readDocumentGIObjects(
      final String objaddress,
      final Integer docstate,
      final String bostate,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadDocumentGIObjects readDocumentGIObjects = objectFactory.createReadDocumentGIObjects();
    readDocumentGIObjects.setUserlogin(nutzer);
    readDocumentGIObjects.setBusinessapp(anwendung);
    readDocumentGIObjects.setObjaddress(objaddress);
    readDocumentGIObjects.setJoboe(organisationseinheit);
    readDocumentGIObjects.setJobposition(rolle);
    readDocumentGIObjects.setDocstate(docstate);
    readDocumentGIObjects.setBostate(bostate);
    return readDocumentGIObjects;
  }
}
