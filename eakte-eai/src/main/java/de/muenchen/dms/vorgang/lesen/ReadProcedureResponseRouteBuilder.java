/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.lesen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_PROCEDURE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadProcedureResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final ReadProcedureProcessor vorgangLesenProcessor;
  private final ReadProcedureResponseProcessor vorgangLesenResponseProcessor;

  public ReadProcedureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadProcedureProcessor vorgangLesenProcessor,
      ReadProcedureResponseProcessor vorgangLesenResponseProcessor) {
    super(authorizationProcessor);
    this.vorgangLesenProcessor = vorgangLesenProcessor;
    this.vorgangLesenResponseProcessor = vorgangLesenResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_PROCEDURE)
        .request(vorgangLesenProcessor)
        .response(vorgangLesenResponseProcessor);
  }
}
