/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception;

import de.muenchen.dms.common.route.exception.general.DmsGeneralExceptionConfiguration;
import de.muenchen.dms.common.route.exception.not_found.DmsEndpointNotFoundConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration(proxyBeanMethods = false)
@Import({DmsGeneralExceptionConfiguration.class, DmsEndpointNotFoundConfiguration.class})
public class DmsExceptionConfiguration {
  /**
   * Creates a route that logs a given exception
   *
   * @return a route builder
   */
  @Bean
  public DmsLogExceptionRouteBuilder dmsLogExceptionRouteBuilder() {
    return new DmsLogExceptionRouteBuilder();
  }
}
