/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_OUTGOING;

import org.apache.camel.model.rest.RestDefinition;
import org.springframework.stereotype.Component;

@Component
public class CreateOutgoingRouteBuilder extends CreateOutgoingBasisRouteBuilder {
  @Override
  protected RestDefinition restRouteDefinition() {
    return rest("/outgoings").post().id(ROUTE_ID_CREATE_OUTGOING).description("Ausgang anlegen");
  }
}
