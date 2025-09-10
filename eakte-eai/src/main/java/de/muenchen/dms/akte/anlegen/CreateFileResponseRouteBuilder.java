/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_FILE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateFileResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final CreateFileProcessor akteAnlegenProcessor;
  private final CreateFileResponseProcessor akteAnlegenResponseProcessor;

  public CreateFileResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateFileProcessor akteAnlegenProcessor,
      CreateFileResponseProcessor akteAnlegenResponseProcessor) {
    super(authorizationProcessor);
    this.akteAnlegenProcessor = akteAnlegenProcessor;
    this.akteAnlegenResponseProcessor = akteAnlegenResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_CREATE_FILE)
        .request(akteAnlegenProcessor)
        .unmarshal(CreateFileDTO.class)
        .response(akteAnlegenResponseProcessor);
  }
}
