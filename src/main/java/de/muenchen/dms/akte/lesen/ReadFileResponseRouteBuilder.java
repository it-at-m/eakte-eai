/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.lesen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_FILE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadFileResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final ReadFileProcessor akteLesenProcessor;
  private final ReadFileResponseProcessor akteLesenResponseProcessor;

  public ReadFileResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadFileProcessor akteLesenProcessor,
      ReadFileResponseProcessor akteLesenResponseProcessor) {
    super(authorizationProcessor);
    this.akteLesenProcessor = akteLesenProcessor;
    this.akteLesenResponseProcessor = akteLesenResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_FILE).request(akteLesenProcessor).response(akteLesenResponseProcessor);
  }
}
