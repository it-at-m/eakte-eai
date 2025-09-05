package de.muenchen.dms.common.route;

import de.muenchen.dms.common.processor.PayloadLogger;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class PayloadLoggerRouteBuilder extends RouteBuilder {

  @Override
  public void configure() throws Exception {
    from(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .routeId(RouteConstants.ROUTE_ID_PAYLOAD_LOGGING)
        .process(new PayloadLogger(RouteConstants.REQ_IN))
        .toD(RouteConstants.PRODUCER_URL)
        .process(new PayloadLogger(RouteConstants.RESP_IN));
  }
}
