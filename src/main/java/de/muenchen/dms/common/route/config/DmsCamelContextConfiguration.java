/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.config;

import org.apache.camel.CamelContext;
import org.apache.camel.spring.boot.CamelContextConfiguration;

public class DmsCamelContextConfiguration implements CamelContextConfiguration {
  @Override
  public void beforeApplicationStart(CamelContext camelContext) {
    camelContext.setUseMDCLogging(Boolean.TRUE);
  }

  @Override
  public void afterApplicationStart(CamelContext camelContext) {
    // Nach dem Anwendungsstart sind keine Aufgaben auszuführen
  }
}
