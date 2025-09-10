/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@NoArgsConstructor
@Getter
@Component
public class ApplicationProperties {

  @Value("${info.app.name}")
  private String appName;

  @Value("${info.app.description}")
  private String appDescription;

  @Value("${info.app.version}")
  private String appVersion;

  @Value("${info.app.build.date}")
  private String appBuildDate;

  @Value("${info.app.contact.name}")
  private String appContactName;

  @Value("${info.app.contact.url}")
  private String appContactUrl;

  @Value("${swagger.schemes}")
  private String swaggerSchemes;

  @Value("${eai.baseurl}")
  private String baseUrl;

  @Value("${server.baseurl}")
  private String serverBaseurl;

  @Value("${dms.url}")
  private String dmsUrl;

  @Value("${build.dms-eai.version}")
  private String dmsEaiVersion;
}
