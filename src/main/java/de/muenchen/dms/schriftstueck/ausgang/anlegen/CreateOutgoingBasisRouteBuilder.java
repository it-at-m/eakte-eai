/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_OUTGOING;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.apache.camel.model.rest.RestDefinition;
import org.springframework.http.MediaType;

abstract class CreateOutgoingBasisRouteBuilder extends DmsRouteBuilder {

  /**
   * Erzeugt eine Route für eingehende REST-Aufrufe. Deren Ziel ist die Anlage von Schriftstücken /
   * Dateien im DMS.
   *
   * @throws Exception Ausnahmefehler
   */
  @Override
  public final void route() throws Exception {
    restRouteDefinition()
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        // describe requestBody using type class and Schema annotations
        .type(CreateOutgoingRequestBodyDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateOutgoingAntwortDTO.class))
        .to(DIRECT_CREATE_OUTGOING);
  }

  protected abstract RestDefinition restRouteDefinition();
}
