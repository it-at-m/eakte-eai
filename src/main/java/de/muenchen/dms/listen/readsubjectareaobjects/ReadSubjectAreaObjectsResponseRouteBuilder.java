package de.muenchen.dms.listen.readsubjectareaobjects;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_SUBJECT_AREA_OBJECT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaObjectsResponseRouteBuilder extends DmsResponseRouteBuilder {

  private final ReadSubjectAreaObjectsProcessor requestProcessor;
  private final ReadSubjectAreaObjectsResponseProcessor responseProcessor;

  public ReadSubjectAreaObjectsResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      ReadSubjectAreaObjectsProcessor requestProcessor,
      ReadSubjectAreaObjectsResponseProcessor responseProcessor) {
    super(authorizationProcessor);
    this.requestProcessor = requestProcessor;
    this.responseProcessor = responseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_READ_SUBJECT_AREA_OBJECT).request(requestProcessor).response(responseProcessor);
  }
}
