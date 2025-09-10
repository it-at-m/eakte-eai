/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2022
 */
package de.muenchen.dms.common;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIWSDSoap;
import de.muenchen.dms.common.route.config.DmsCommonRouteConfigConfiguration;
import org.apache.camel.component.cxf.jaxws.CxfConfigurer;
import org.apache.camel.component.cxf.jaxws.CxfEndpoint;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;

@Configuration
@PropertySource(
    value = {"classpath:application.properties"},
    ignoreResourceNotFound = true)
@Import({DmsCommonRouteConfigConfiguration.class})
public class DmsCommonConfiguration {

  @Value("${dms.default.operation.namespace}")
  private String defaultOperationNamespace;

  private final CxfConfigurer cxfConfigurer;

  public DmsCommonConfiguration(CxfConfigurer cxfConfigurer) {
    this.cxfConfigurer = cxfConfigurer;
  }

  /**
   * Erstellen des CXF-Producers
   *
   * @return CXF-Endpunkt über den die Nachrichten an die DMS EAI versendet werden
   */
  @Bean
  @Scope(
      "request") // we need this, as every customer (Fachverfahren) could have a custom service url
  public CxfEndpoint producer() {
    final CxfEndpoint endpoint = new CxfEndpoint();

    endpoint.setServiceClass(LHMBAI151700GIWSDSoap.class);
    endpoint.setSynchronous(true);
    endpoint.setDefaultOperationNamespace(defaultOperationNamespace);
    endpoint.setMtomEnabled(true);
    endpoint.setCxfConfigurer(cxfConfigurer);
    return endpoint;
  }
}
