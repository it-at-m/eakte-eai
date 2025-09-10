package de.muenchen.dms.common.route.config;

import org.apache.camel.component.cxf.jaxws.CxfConfigurer;
import org.apache.cxf.endpoint.Client;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.frontend.AbstractWSDLBasedEndpointFactory;
import org.apache.cxf.transport.http.HTTPConduit;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CxfConfig implements CxfConfigurer {

  // Specifies the amount of time, in milliseconds, that the consumer will wait for a response
  // before it times out.
  private static final Long RECEIVE_TIMEOUT = 900000L;
  // Specifies the amount of time, in milliseconds, used when requesting a connection from the
  // connection manager(if appliable).
  private static final Long CONNECTION_REQUEST_TIMEOUT = 63000L;
  // Specifies the amount of time, in milliseconds, that the consumer will attempt to establish a
  // connection before it times out.
  private static final Long CONNECTION_TIMEOUT = 63000L;

  @Override
  public void configure(AbstractWSDLBasedEndpointFactory factoryBean) {
    // NO-OP
  }

  @Override
  public void configureClient(Client client) {
    var httpClientPolicy = ((HTTPConduit) client.getConduit()).getClient();
    httpClientPolicy.setReceiveTimeout(RECEIVE_TIMEOUT);
    httpClientPolicy.setConnectionRequestTimeout(CONNECTION_REQUEST_TIMEOUT);
    httpClientPolicy.setConnectionTimeout(CONNECTION_TIMEOUT);
  }

  @Override
  public void configureServer(Server server) {
    // NO-OP
  }
}
