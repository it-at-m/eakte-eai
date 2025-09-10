package de.muenchen.dms.disposalschedule.aendern;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_DISPOSAL_SCHEDULE_DATE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class SetDisposalScheduleDateResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final SetDisposalScheduleDateProcessor setDisposalScheduleDateProcessor;
  private final SetDisposalScheduleDateResponseProcessor setDisposalScheduleDateResponseProcessor;

  public SetDisposalScheduleDateResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      SetDisposalScheduleDateProcessor setDisposalScheduleDateProcessor,
      SetDisposalScheduleDateResponseProcessor setDisposalScheduleDateResponseProcessor) {
    super(authorizationProcessor);
    this.setDisposalScheduleDateProcessor = setDisposalScheduleDateProcessor;
    this.setDisposalScheduleDateResponseProcessor = setDisposalScheduleDateResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_DISPOSAL_SCHEDULE_DATE)
        .request(setDisposalScheduleDateProcessor)
        .unmarshal(SetDisposalScheduleDateAnfrageDTO.class)
        .response(setDisposalScheduleDateResponseProcessor);
  }
}
