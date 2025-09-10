/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.lesen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_CONTENT_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadContentObjectResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final ReadContentObjectProcessor requestProcessor;
  private final ReadContentObjectResponseProcessor responseProcessor;

  public ReadContentObjectResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadContentObjectProcessor requestProcessor,
      ReadContentObjectResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_CONTENT_OBJECT).request(requestProcessor).response(responseProcessor);
  }
}
