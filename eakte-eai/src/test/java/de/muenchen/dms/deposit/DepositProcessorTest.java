package de.muenchen.dms.deposit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.DepositObjectGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Die Testklasse prüft die Entgegennahme von REST-Anfragen zu DepoitObject+")
class DepositProcessorTest {

  @Test
  void testeProcess() throws DatatypeConfigurationException, JsonProcessingException {
    final String OBJADDRESS = "COO.1234.5678.90";
    final String USER = "user";
    final String ANWENDUNG = "anwendung";

    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);

    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, OBJADDRESS);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, USER);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);

    final DepositProcessor depositProcessor = new DepositProcessor();
    depositProcessor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    DepositObjectGI depositObjectGI = exchange.getMessage().getBody(DepositObjectGI.class);

    assertThat(header, equalTo(DepositObjectGI.class.getSimpleName()));
    assertNotNull(depositObjectGI, "depositObjectGI is null");
    assertEquals(USER, depositObjectGI.getUserlogin(), "userlogin is wrong");
    assertEquals(ANWENDUNG, depositObjectGI.getBusinessapp(), "businessapp is wrong");
    assertEquals(OBJADDRESS, depositObjectGI.getObjaddress(), "objaddress is wrong");
  }
}
