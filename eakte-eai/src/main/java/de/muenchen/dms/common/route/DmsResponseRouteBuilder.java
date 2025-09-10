/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route;

import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;

import de.muenchen.dms.common.DmsResponseFluentApi.DmsRequestApi;
import de.muenchen.dms.common.DmsResponseFluentApi.DmsResponseApi;
import de.muenchen.dms.common.DmsResponseFluentApi.DmsResponseFluentApiDone;
import org.apache.camel.Processor;

public abstract class DmsResponseRouteBuilder extends DmsRouteBuilder {
  private Processor authorizationProcessor;

  protected DmsResponseRouteBuilder(Processor authorizationProcessor) {
    this.authorizationProcessor = authorizationProcessor;
  }

  public DmsRequestApi soap(String id) {
    return new RouteSoapData(id);
  }

  private class RouteSoapData implements DmsResponseFluentApiDone, DmsRequestApi, DmsResponseApi {
    private String id;
    private Class<?> classToUnmarshal;
    private Processor requestProcessor;
    private Processor responseProcessor;

    public RouteSoapData(String id) {
      this.id = id;
    }

    @Override
    public DmsResponseApi request(Processor processor) {
      this.requestProcessor = processor;
      return this;
    }

    @Override
    public DmsResponseApi unmarshal(Class<?> clazz) {
      this.classToUnmarshal = clazz;
      return this;
    }

    @Override
    public DmsResponseApi mock(Processor processor) {
      return null;
    }

    @Override
    public void response(Processor processor) {
      this.responseProcessor = processor;
      createRoute();
    }

    public String getId() {
      return id;
    }

    public Class<?> getClassToUnmarshal() {
      return classToUnmarshal;
    }

    public Processor getRequestProcessor() {
      return requestProcessor;
    }

    public Processor getResponseProcessor() {
      return responseProcessor;
    }

    private void createRoute() {
      from(getId())
          .routeId(getId())
          .log(RouteConstants.LOG_MSG_PROCESS_ROUTE)
          .process(authorizationProcessor)
          .choice()
          .when(exchange -> getClassToUnmarshal() != null)
          .unmarshal(getJacksonDataFormat(getClassToUnmarshal()))
          .end()
          .process(getRequestProcessor())
          .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
          .process(getResponseProcessor());
    }
  }
}
