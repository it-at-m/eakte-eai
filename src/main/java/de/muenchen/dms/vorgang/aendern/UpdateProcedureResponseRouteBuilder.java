/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.aendern;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_PROCEDURE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateProcedureResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final UpdateProcedureProcessor updateProcedureProcessor;
  private final UpdateProcedureResponseProcessor updateProcedureResponseProcessor;

  public UpdateProcedureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateProcedureProcessor updateProcedureProcessor,
      UpdateProcedureResponseProcessor updateProcedureResponseProcessor) {
    super(authorizationProcessor);
    this.updateProcedureProcessor = updateProcedureProcessor;
    this.updateProcedureResponseProcessor = updateProcedureResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_UPDATE_PROCEDURE)
        .request(updateProcedureProcessor)
        .unmarshal(UpdateProcedureDTO.class)
        .response(updateProcedureResponseProcessor);
  }
}
