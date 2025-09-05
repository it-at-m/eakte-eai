package de.muenchen.dms.listen.readsubjectareaobjects;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadSubjectAreaGIObjects;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaObjectsProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) {
    final Integer docstate = exchange.getProperty(QueryProperties.PROPERTY_DOCSTATE, Integer.class);
    final String bostate = exchange.getProperty(QueryProperties.PROPERTY_BOSTATE, String.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final ReadSubjectAreaGIObjects parameters =
        readSubjectAreaGIObjects(
            objaddress,
            docstate,
            bostate,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadSubjectAreaGIObjects.class);
  }

  private ReadSubjectAreaGIObjects readSubjectAreaGIObjects(
      final String objaddress,
      final Integer docstate,
      final String bostate,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadSubjectAreaGIObjects readSubjectAreaGIObjects =
        objectFactory.createReadSubjectAreaGIObjects();
    readSubjectAreaGIObjects.setUserlogin(nutzer);
    readSubjectAreaGIObjects.setBusinessapp(anwendung);
    readSubjectAreaGIObjects.setObjaddress(objaddress);
    readSubjectAreaGIObjects.setJoboe(organisationseinheit);
    readSubjectAreaGIObjects.setJobposition(rolle);
    readSubjectAreaGIObjects.setDocstate(docstate);
    readSubjectAreaGIObjects.setBostate(bostate);
    return readSubjectAreaGIObjects;
  }
}
