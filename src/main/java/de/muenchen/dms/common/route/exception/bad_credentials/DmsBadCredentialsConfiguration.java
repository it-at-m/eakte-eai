/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception.bad_credentials;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
public class DmsBadCredentialsConfiguration {
  /**
   * Erstellt eine Route, die Aufgerufen wird wenn die Credentials nicht stimmen
   *
   * @return route builder
   */
  @Bean
  public DmsBadCredentialsRouteBuilder dmsBadCredentialsRouteBuilder() {
    return new DmsBadCredentialsRouteBuilder();
  }

  /**
   * Erstellt eine RoutenConfiguration, die zu einer Camel Route weiterleitet, wenn die Credentials
   * nicht stimmen
   *
   * @return route configuration builder
   */
  @Bean
  public DmsBadCredentialsExceptionHandler dmsBadCredentialsExceptionHandler() {
    return new DmsBadCredentialsExceptionHandler();
  }
}
