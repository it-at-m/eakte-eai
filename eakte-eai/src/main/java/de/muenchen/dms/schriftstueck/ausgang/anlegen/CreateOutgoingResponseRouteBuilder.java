/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_OUTGOING;
import static de.muenchen.dms.common.route.RouteConstants.LOG_MSG_PROCESS_ROUTE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_OUTGOING_VERARBEITUNG;
import static de.muenchen.dms.common.util.JacksonData.getJacksonDataFormat;
import static de.muenchen.dms.common.util.JacksonData.getMimeMultipartDataFormat;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateOutgoingResponseRouteBuilder extends DmsRouteBuilder {
  private final DmsAuthorizationProcessor authorizationProcessor;
  private final CreateOutgoingProcessor ausgangAnlegenProcessor;
  private final CreateOutgoingResponseProcessor ausgangAnlegenResponseProcessor;

  /**
   * Erzeugt die Route, die zur Beantwortung einer REST-Anfrage nach einem neuen Ausgangsdokument
   * angelegt wurde.
   *
   * @param authorizationProcessor ein Verarbeitungsprozess, um den DMS-Benutzer zu authorisieren
   * @param ausgangAnlegenProcessor ein Verarbeitungsprozess, um den eingehende REST-Nachricht zu
   *     verarbeiten
   * @param ausgangAnlegenResponseProcessor ein Verarbeitungsprozess, um das DMS mit den
   *     Anfragedaten zu kontaktieren
   */
  public CreateOutgoingResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateOutgoingProcessor ausgangAnlegenProcessor,
      CreateOutgoingResponseProcessor ausgangAnlegenResponseProcessor) {
    this.authorizationProcessor = authorizationProcessor;
    this.ausgangAnlegenProcessor = ausgangAnlegenProcessor;
    this.ausgangAnlegenResponseProcessor = ausgangAnlegenResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    from(DIRECT_CREATE_OUTGOING)
        .routeId(ROUTE_ID_CREATE_OUTGOING_VERARBEITUNG)
        .log(LOG_MSG_PROCESS_ROUTE)
        .process(authorizationProcessor)
        .unmarshal(getMimeMultipartDataFormat())
        .unmarshal(getJacksonDataFormat(CreateOutgoingAnfrageDTO.class))
        .process(ausgangAnlegenProcessor)
        .toD(RouteConstants.DIRECT_PAYLOAD_LOGGING_ENDPOINT)
        .process(ausgangAnlegenResponseProcessor);
  }
}
