package de.muenchen.dms.akte.readsubjectareafile;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_SUBJECT_AREA_FILE;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaFileResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadSubjectAreaFileProcessor requestProcessor;
  private final ReadSubjectAreaFileResponseProcessor responseProcessor;

  public ReadSubjectAreaFileResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadSubjectAreaFileProcessor requestProcessor,
      ReadSubjectAreaFileResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_SUBJECT_AREA_FILE).request(requestProcessor).response(responseProcessor);
  }
}
