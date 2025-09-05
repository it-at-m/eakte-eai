/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.lesen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadFileGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ReadFileProcessorTest {
  @Test
  void testProcess() throws DatatypeConfigurationException, JsonProcessingException {
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
    final ReadFileProcessor processor = new ReadFileProcessor();
    processor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    ReadFileGI readFileGI = exchange.getMessage().getBody(ReadFileGI.class);

    assertThat(header, equalTo(ReadFileGI.class.getSimpleName()));
    Assertions.assertNotNull(readFileGI, "UpdateFileGI Objekt ist null");
    Assertions.assertEquals(NUTZER, readFileGI.getUserlogin(), "Userlogin stimmt nicht überein");
    Assertions.assertEquals(
        ANWENDUNG, readFileGI.getBusinessapp(), "Businessapp stimmt nicht überein");
    Assertions.assertEquals(
        AKTENCOOID, readFileGI.getObjaddress(), "Objadress stimmt nicht überein");
  }
}
