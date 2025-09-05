package de.muenchen.dms.addressee.add;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.RouteConstants;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class AddAddresseeResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final AddAddresseeProcessor addAddresseeProcessor;
  private final AddAddresseeResponseProcessor addAddresseeResponseProcessor;

  public AddAddresseeResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      AddAddresseeProcessor addAddresseeProcessor,
      AddAddresseeResponseProcessor addAddresseeResponseProcessor) {
    super(authorizationProcessor);
    this.addAddresseeProcessor = addAddresseeProcessor;
    this.addAddresseeResponseProcessor = addAddresseeResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(RouteConstants.DIRECT_ADD_ADDRESSEE)
        .request(addAddresseeProcessor)
        .unmarshal(AddAddresseeRequestDTO.class)
        .response(addAddresseeResponseProcessor);
  }
}
