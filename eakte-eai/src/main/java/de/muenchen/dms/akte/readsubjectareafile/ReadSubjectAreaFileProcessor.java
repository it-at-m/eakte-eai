package de.muenchen.dms.akte.readsubjectareafile;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadSubjectAreaFileGIObjects;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaFileProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final Integer docstate = exchange.getProperty(QueryProperties.PROPERTY_DOCSTATE, Integer.class);
    final String bostate = exchange.getProperty(QueryProperties.PROPERTY_BOSTATE, String.class);
    final String aktencooid =
        exchange.getMessage().getHeader(RouteConstants.PATH_PARAM_OBJADDRESS, String.class);
    // Create SOAP input.
    final ReadSubjectAreaFileGIObjects parameters =
        readSubjectAreaFileGIObjects(
            aktencooid,
            getNutzer(exchange),
            docstate,
            bostate,
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, ReadSubjectAreaFileGIObjects.class);
  }

  private ReadSubjectAreaFileGIObjects readSubjectAreaFileGIObjects(
      final String objaddress,
      final String userlogin,
      final Integer docstate,
      final String bostate,
      final String joboe,
      final String jobposition,
      final String businessapp) {
    final ReadSubjectAreaFileGIObjects readSubjectAreaFileGIObjects =
        objectFactory.createReadSubjectAreaFileGIObjects();
    readSubjectAreaFileGIObjects.setUserlogin(userlogin);
    readSubjectAreaFileGIObjects.setObjaddress(objaddress);
    readSubjectAreaFileGIObjects.setDocstate(docstate);
    readSubjectAreaFileGIObjects.setBostate(bostate);
    readSubjectAreaFileGIObjects.setJobposition(jobposition);
    readSubjectAreaFileGIObjects.setJoboe(joboe);
    readSubjectAreaFileGIObjects.setBusinessapp(businessapp);
    return readSubjectAreaFileGIObjects;
  }
}
