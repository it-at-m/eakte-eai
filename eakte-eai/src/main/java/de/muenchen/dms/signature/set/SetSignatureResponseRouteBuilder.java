package de.muenchen.dms.signature.set;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SetSignatureResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final SetSignatureProcessor setSignatureProcessor;
  private final SetSignatureResponseProcessor setSignatureResponseProcessor;

  public SetSignatureResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SetSignatureProcessor setSignatureProcessor,
      SetSignatureResponseProcessor setSignatureResponseProcessor) {
    super(authorizationProcessor);
    this.setSignatureProcessor = setSignatureProcessor;
    this.setSignatureResponseProcessor = setSignatureResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_SET_SIGNATURE)
        .request(setSignatureProcessor)
        .unmarshal(SetSignatureRequestDTO.class)
        .response(setSignatureResponseProcessor);
  }
}
