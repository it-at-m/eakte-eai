package de.muenchen.dms.schriftstueck.ausgang_lesen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadOutgoingGI;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.schriftstueck.ausgang.lesen.ReadOutgoingProcessor;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ReadOutgoingProcessorTest {
  static final String DATEINAME_MIT_SONDERZEICHEN = "!@#$%^&*()_+|}{[],><?-~.txt";
  static final String BEISPIEL_VORGANG_ID = "COO.1.2301.1.1042432";

  @Test
  void testProcess() throws Exception {
    final String AKTENCOOID = "COOID 1212";
    final String NUTZER = "NUTZER";
    final String ANWENDUNG = "ANWENDUNG";

    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);

    // Exchange anreichern mit den erforderlichen Daten
    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, AKTENCOOID);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, NUTZER);

    // Methode ausführen
    final ReadOutgoingProcessor processor = new ReadOutgoingProcessor();
    processor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    ReadOutgoingGI readOutgoingGI = exchange.getMessage().getBody(ReadOutgoingGI.class);

    assertThat(header, equalTo(ReadOutgoingGI.class.getSimpleName()));
    Assertions.assertNotNull(readOutgoingGI, "ReadOutgoingGI Objekt ist null");
    Assertions.assertEquals(
        NUTZER, readOutgoingGI.getUserlogin(), "Userlogin stimmt nicht überein");
    Assertions.assertEquals(
        ANWENDUNG, readOutgoingGI.getBusinessapp(), "Businessapp stimmt nicht überein");
    Assertions.assertEquals(
        AKTENCOOID, readOutgoingGI.getObjaddress(), "Objadress stimmt nicht überein");
  }
}
