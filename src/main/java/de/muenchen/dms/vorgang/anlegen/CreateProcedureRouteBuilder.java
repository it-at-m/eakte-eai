/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_PROCEDURE;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_PROCEDURE;

import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/procedures")
        .post()
        .id(ROUTE_ID_CREATE_PROCEDURE)
        .description("Vorgang anlegen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateProcedureDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(DmsObjektResponse.class))
        .to(DIRECT_CREATE_PROCEDURE);
  }
}
