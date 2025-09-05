/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.lesen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGI;
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

class ReadProcedureProcessorTest {
  @Test
  void testProcess() throws DatatypeConfigurationException, JsonProcessingException {
    final String VORGANGCOOID = "COOID 1212";
    final String NUTZER = "NUTZER";
    final String ANWENDUNG = "ANWENDUNG";
    final String ORGANISATIONSEINHEIT = "COO.2150.9150.1.3359";
    final String STELLE = "DocumentManager";

    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);

    // Exchange anreichern mit den erforderlichen Daten
    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, VORGANGCOOID);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, NUTZER);
    exchange.setProperty(QueryProperties.PROPERTY_ORGANISATIONSEINHEIT, ORGANISATIONSEINHEIT);
    exchange.setProperty(QueryProperties.PROPERTY_ROLLE, STELLE);

    // Methode ausführen
    final ReadProcedureProcessor processor = new ReadProcedureProcessor();
    processor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    ReadProcedureGI readProcedureGI = exchange.getMessage().getBody(ReadProcedureGI.class);

    assertThat(header, equalTo(ReadProcedureGI.class.getSimpleName()));
    Assertions.assertNotNull(readProcedureGI, "UpdateProcedureGI Objekt ist null");
    Assertions.assertEquals(
        NUTZER, readProcedureGI.getUserlogin(), "Userlogin stimmt nicht überein");
    Assertions.assertEquals(
        ANWENDUNG, readProcedureGI.getBusinessapp(), "Businessapp stimmt nicht überein");
    Assertions.assertEquals(
        VORGANGCOOID, readProcedureGI.getObjaddress(), "Objadress stimmt nicht überein");
    Assertions.assertEquals(
        ORGANISATIONSEINHEIT, readProcedureGI.getJoboe(), "Joboe stimmt icht überein");
    Assertions.assertEquals(
        STELLE, readProcedureGI.getJobposition(), "Jobposition stimmt nicht überein");
  }
}
