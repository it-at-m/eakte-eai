/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_INCOMING;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_INCOMING_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingResponseRouteBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateIncomingBasisProcessor createIncomingProcessor;
  private final CreateIncomingResponseProcessor createIncomingResponseProcessor;

  public CreateIncomingResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateIncomingBasisProcessor createIncomingProcessor,
      CreateIncomingResponseProcessor createIncomingResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.createIncomingProcessor = createIncomingProcessor;
    this.createIncomingResponseProcessor = createIncomingResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_CREATE_INCOMING)
        .routeId(ROUTE_ID_CREATE_INCOMING_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateIncomingBasisAnfrageDTO.class))
        .process(createIncomingProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(createIncomingResponseProcessor);
  }
}
