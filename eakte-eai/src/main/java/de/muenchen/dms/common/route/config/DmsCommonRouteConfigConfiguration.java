/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.config;

import jakarta.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration(proxyBeanMethods = false)
public class DmsCommonRouteConfigConfiguration {
  // Hint: Route configurations are executed before route builders automatically.
  // There is no need to deal with ordering here.

  /**
   * Basic URI context mappings
   *
   * @return a web mvc configurer
   */
  @Bean
  public WebMvcConfigurer dmsBasicContext() {
    return new DmsBasicContext();
  }

  /**
   * Configures all DMS specific Camel routes.
   *
   * @return a Camel route configuration
   */
  @Bean
  public DmsRouteConfigurationBuilder dmsRouteConfigurationBuilder(
      ServletContext servletContext,
      @Value("${de.muenchen.rest.api.path}") String apiPath,
      @Value("${de.muenchen.rest.api.version}") String apiVersion) {

    return new DmsRouteConfigurationBuilder(servletContext, apiPath, apiVersion);
  }

  /**
   * General Camel settings
   *
   * @return camel context configuration
   */
  @Bean
  public DmsCamelContextConfiguration dmsCamelContextConfiguration() {
    return new DmsCamelContextConfiguration();
  }
}
