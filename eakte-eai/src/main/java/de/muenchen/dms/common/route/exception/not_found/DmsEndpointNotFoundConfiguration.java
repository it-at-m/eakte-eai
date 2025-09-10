/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.not_found;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DmsEndpointNotFoundConfiguration {
  /**
   * Creates a route that is called if an endpoint could not be reached.
   *
   * @return route builder
   */
  @Bean
  public DmsEndpointNotFoundRouteBuilder dmsEndpointNotFoundRouteBuilder(
      DmsEndpointNotFoundProcessor processor) {
    return new DmsEndpointNotFoundRouteBuilder(processor);
  }

  /**
   * Creates a route configuration that forwards to a Camel route in case an endpoint could not be
   * reached.
   *
   * @return route configuration builder
   */
  @Bean
  public DmsEndpointNotFoundExceptionHandler dmsEndpointNotFoundRouteConfigurationBuilder() {
    return new DmsEndpointNotFoundExceptionHandler();
  }

  /**
   * Creates a camel processor that is called in case an error occurred.
   *
   * @return a camel processor
   */
  @Bean
  public DmsEndpointNotFoundProcessor dmsEndpointNotFoundProcessor() {
    return new DmsEndpointNotFoundProcessor();
  }
}
