/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_PROCEDURE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final CreateProcedureProcessor vorgangAnlegenProcessor;
  private final CreateProcedureResponseProcessor vorgangAnlegenResponseProcessor;

  public CreateProcedureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateProcedureProcessor vorgangAnlegenProcessor,
      CreateProcedureResponseProcessor vorgangAnlegenResponseProcessor) {
    super(authorizationProcessor);
    this.vorgangAnlegenProcessor = vorgangAnlegenProcessor;
    this.vorgangAnlegenResponseProcessor = vorgangAnlegenResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_CREATE_PROCEDURE)
        .request(vorgangAnlegenProcessor)
        .unmarshal(CreateProcedureDTO.class)
        .response(vorgangAnlegenResponseProcessor);
  }
}
