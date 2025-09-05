package de.muenchen.dms.listen.readprocedureobjects;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIObjects;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Test;

public class ReadProcedureObjectsProcessorTest {
  @Test
  void testProcess() throws DatatypeConfigurationException, JsonProcessingException {
    final String AKTENCOOID = "COOID 1212";
    final String NUTZER = "NUTZER";
    final String ANWENDUNG = "ANWENDUNG";

    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);

    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, AKTENCOOID);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, NUTZER);

    final ReadProcedureObjectsProcessor processor = new ReadProcedureObjectsProcessor();
    processor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    ReadProcedureGIObjects readProcedureGIObjects =
        exchange.getMessage().getBody(ReadProcedureGIObjects.class);

    assertThat(header, equalTo(ReadProcedureGIObjects.class.getSimpleName()));
    assertNotNull(readProcedureGIObjects, "Objekt ist null");
    assertEquals(NUTZER, readProcedureGIObjects.getUserlogin(), "Userlogin stimmt nicht überein");
    assertEquals(
        ANWENDUNG, readProcedureGIObjects.getBusinessapp(), "Businessapp stimmt nicht überein");
    assertEquals(
        AKTENCOOID, readProcedureGIObjects.getObjaddress(), "Objadress stimmt nicht überein");
  }
}
