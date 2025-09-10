package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import org.apache.camel.builder.RouteBuilder;

public class TestDmsAusgangBackendRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    from("cxf:CreateOutgoingGISoapIn?serviceClass=de.muenchen.dms.ausgang.anlegen.TestAusgangAnlegenWebService&address=/services/dms")
        .log("Faked DMS: ${body}");
  }
}
