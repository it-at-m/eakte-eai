/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_INCOMING;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_INCOMING;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateIncomingRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/incomings")
        .post()
        .id(ROUTE_ID_CREATE_INCOMING)
        .description("Eingang anlegen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateIncomingRequestBodyDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateIncomingAntwortDTO.class))
        .to(DIRECT_CREATE_INCOMING);
  }
}
