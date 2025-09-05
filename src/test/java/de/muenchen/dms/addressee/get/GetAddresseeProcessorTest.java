package de.muenchen.dms.addressee.get;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.GetAddressee;
import de.muenchen.dms.common.route.QueryProperties;
import de.muenchen.dms.common.route.RouteConstants;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class GetAddresseeProcessorTest {

  @Test
  void testProcess() throws Exception {
    final String objaddress = "COO1234";
    final String userlogin = "USER";
    final String jobposition = "JOBPOSITION";
    final String joboe = "JOBOE";
    final String anwendung = "ANWENDUNG";

    CamelContext context = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(context);

    exchange.getIn().setHeader(RouteConstants.PATH_PARAM_OBJADDRESS, objaddress);
    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, userlogin);
    exchange.setProperty(QueryProperties.PROPERTY_ROLLE, jobposition);
    exchange.setProperty(QueryProperties.PROPERTY_ORGANISATIONSEINHEIT, joboe);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, anwendung);

    final GetAddresseeProcessor processor = new GetAddresseeProcessor();
    processor.process(exchange);

    String header = exchange.getMessage().getHeader(CxfConstants.OPERATION_NAME, String.class);
    GetAddressee getAddressee = exchange.getMessage().getBody(GetAddressee.class);

    assertThat(header, equalTo(GetAddressee.class.getSimpleName()));
    Assertions.assertNotNull(getAddressee, "GetAddressee is null");
    Assertions.assertEquals(
        userlogin, getAddressee.getUserlogin(), "Userlogin stimmt nicht überein");
    Assertions.assertEquals(
        objaddress, getAddressee.getObjaddress(), "Objaddress stimmt nich überein");
    Assertions.assertEquals(
        jobposition, getAddressee.getJobposition(), "Jobposition stimmt nicht überein");
    Assertions.assertEquals(joboe, getAddressee.getJoboe(), "Joboe stimmt nicht überein");
    Assertions.assertEquals(
        anwendung, getAddressee.getBusinessapp(), "Businessapp stimmt nicht überein");
  }
}
