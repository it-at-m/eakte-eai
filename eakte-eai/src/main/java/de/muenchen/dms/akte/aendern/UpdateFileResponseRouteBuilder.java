/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.aendern;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_FILE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateFileResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final UpdateFileProcessor updateFileProcessor;
  private final UpdateFileResponseProcessor updateFileResponseProcessor;

  public UpdateFileResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateFileProcessor updateFileProcessor,
      UpdateFileResponseProcessor updateFileResponseProcessor) {
    super(authorizationProcessor);
    this.updateFileProcessor = updateFileProcessor;
    this.updateFileResponseProcessor = updateFileResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_UPDATE_FILE)
        .request(updateFileProcessor)
        .unmarshal(UpdateFileDTO.class)
        .response(updateFileResponseProcessor);
  }
}
