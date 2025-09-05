package de.muenchen.dms.aktenplaneintrag.update;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_UPDATE_SUBJECT_AREA;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class UpdateSubjectAreaResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final UpdateSubjectAreaProcessor updateSubjectAreaProcessor;
  private final UpdateSubjectAreaResponseProcessor updateSubjectAreaResponseProcessor;

  public UpdateSubjectAreaResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      UpdateSubjectAreaProcessor updateSubjectAreaProcessor,
      UpdateSubjectAreaResponseProcessor updateSubjectAreaResponseProcessor) {
    super(authorizationProcessor);
    this.updateSubjectAreaProcessor = updateSubjectAreaProcessor;
    this.updateSubjectAreaResponseProcessor = updateSubjectAreaResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_UPDATE_SUBJECT_AREA)
        .request(updateSubjectAreaProcessor)
        .unmarshal(UpdateSubjectAreaRequestDTO.class)
        .response(updateSubjectAreaResponseProcessor);
  }
}
