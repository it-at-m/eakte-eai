/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.lesen;

import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_CONTENT_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/contentObjects")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_CONTENT_OBJECT)
        .description("Schriftstück lesen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadContentObjectResponseDTO.class))
        .to(RouteConstants.DIRECT_READ_CONTENT_OBJECT);
  }
}
