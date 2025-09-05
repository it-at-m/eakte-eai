package de.muenchen.dms.deposit.revoke;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.RevokeDepositObjectGI;
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
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Die Testklasse prüft die Entgegennahme von REST-Anfragen zu Revoke Deposit")
public class RevokeDepositProcessorTest {

  @Test
  void testeProcess() throws DatatypeConfigurationException, JsonProcessingException {
    final String OBJADDRESS = "COO.1234.5678.90";
    final String USER = "user";
    final String ANWENDUNG = "anwendung";

    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);

    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, OBJADDRESS);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, USER);

    final RevokeDepositProcessor revokeDepositProcessor = new RevokeDepositProcessor();
    revokeDepositProcessor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    RevokeDepositObjectGI revokeDepositObjectGI =
        exchange.getMessage().getBody(RevokeDepositObjectGI.class);

    assertThat(header, equalTo(RevokeDepositObjectGI.class.getSimpleName()));
    Assertions.assertNotNull(revokeDepositObjectGI, "RevokeDepositObjectGI is null");
    Assertions.assertEquals(
        USER, revokeDepositObjectGI.getUserlogin(), "Userlogin stimmt nicht überein");
    Assertions.assertEquals(
        ANWENDUNG, revokeDepositObjectGI.getBusinessapp(), "Businessapp stimmt nicht überein");
    Assertions.assertEquals(
        OBJADDRESS, revokeDepositObjectGI.getObjaddress(), "Objaddress stimmt nicht überein");
  }
}
