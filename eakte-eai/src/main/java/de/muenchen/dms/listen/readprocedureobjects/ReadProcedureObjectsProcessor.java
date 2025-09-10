package de.muenchen.dms.listen.readprocedureobjects;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIObjects;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadProcedureObjectsProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) {
    final Integer docstate = exchange.getProperty(QueryProperties.PROPERTY_DOCSTATE, Integer.class);
    final String bostate = exchange.getProperty(QueryProperties.PROPERTY_BOSTATE, String.class);
    final String objaddress =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);

    final ReadProcedureGIObjects parameters =
        readProcedureGIObjects(
            objaddress,
            docstate,
            bostate,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadProcedureGIObjects.class);
  }

  private ReadProcedureGIObjects readProcedureGIObjects(
      final String objaddress,
      final Integer docstate,
      final String bostate,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final ReadProcedureGIObjects readProcedureGIObjects =
        objectFactory.createReadProcedureGIObjects();
    readProcedureGIObjects.setUserlogin(nutzer);
    readProcedureGIObjects.setBusinessapp(anwendung);
    readProcedureGIObjects.setObjaddress(objaddress);
    readProcedureGIObjects.setJoboe(organisationseinheit);
    readProcedureGIObjects.setJobposition(rolle);
    readProcedureGIObjects.setDocstate(docstate);
    readProcedureGIObjects.setBostate(bostate);
    return readProcedureGIObjects;
  }
}
