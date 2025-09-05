package de.muenchen.dms.schriftstueck.eingang.anlegen;

import org.apache.camel.builder.RouteBuilder;

public class TestDmsEingangBackendRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("cxf:CreateIncomingGISoapIn?serviceClass=de.muenchen.dms.ausgang.anlegen.TestEingangAnlegenWebService&address=/services/dms")
        .log("Faked DMS: ${body}");
  }
}
