/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.config;

import jakarta.servlet.ServletContext;
import org.apache.camel.builder.RouteConfigurationBuilder;
import org.apache.camel.model.rest.RestBindingMode;

public class DmsRouteConfigurationBuilder extends RouteConfigurationBuilder {
  private final ServletContext servletContext;
  private final String apiPath;

  private final String apiVersion;

  public DmsRouteConfigurationBuilder(
      ServletContext servletContext, String apiPath, String apiVersion) {
    this.servletContext = servletContext;
    this.apiPath = apiPath;
    this.apiVersion = apiVersion;
  }

  @Override
  public void configuration() throws Exception {
    restConfiguration()
        .component("servlet")
        // automatic bind multipart/form-data as attachments on the Camel Exchange
        .endpointProperty("attachmentMultipartBinding", "true")
        // must be off because otherwise it trys to convert the multipart/formdata to json and fails
        .bindingMode(RestBindingMode.off)
        .contextPath(servletContext.getContextPath() + apiPath)
        .apiProperty("api.title", "DMS REST API")
        .apiProperty("api.version", apiVersion)
        // and enable CORS
        .apiProperty("cors", "true");
  }
}
