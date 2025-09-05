/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.general;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DmsGeneralExceptionConfiguration {
  /**
   * Camel route to handle general exceptions
   *
   * @param exceptionProcessor handles general exception
   * @return a Camel route builder
   */
  @Bean
  public DmsGeneralExceptionRouteBuilder dmsGeneralExceptionRouteBuilder(
      DmsGeneralExceptionProcessor exceptionProcessor) {
    return new DmsGeneralExceptionRouteBuilder(exceptionProcessor);
  }

  /**
   * Camel route configuration for general exceptions
   *
   * @return a Camel route configuration builder
   */
  @Bean
  public DmsGeneralExceptionHandler dmsGeneralExceptionRouteConfigurationBuilder() {
    return new DmsGeneralExceptionHandler();
  }

  @Bean
  public DmsGeneralExceptionProcessor dmsGeneralExceptionProcessor() {
    return new DmsGeneralExceptionProcessor();
  }
}
