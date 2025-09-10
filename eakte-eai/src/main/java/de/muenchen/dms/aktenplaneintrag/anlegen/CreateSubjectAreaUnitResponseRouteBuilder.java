package de.muenchen.dms.aktenplaneintrag.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_SUBJECT_AREA_UNIT;

import de.muenchen.dms.common.route.DmsResponseRouteBuilder;
import de.muenchen.dms.common.route.auth.DmsAuthorizationProcessor;
import org.springframework.stereotype.Component;

@Component
public class CreateSubjectAreaUnitResponseRouteBuilder extends DmsResponseRouteBuilder {
  private final CreateSubjectAreaUnitProcessor createSubjectAreaUnitGIProcessor;
  private final CreateSubjectAreaUnitResponseProcessor createSubjectAreaUnitGIResponseProcessor;

  public CreateSubjectAreaUnitResponseRouteBuilder(
      DmsAuthorizationProcessor authorizationProcessor,
      CreateSubjectAreaUnitProcessor createSubjectAreaUnitGIProcessor,
      CreateSubjectAreaUnitResponseProcessor createSubjectAreaUnitGIResponseProcessor) {
    super(authorizationProcessor);
    this.createSubjectAreaUnitGIProcessor = createSubjectAreaUnitGIProcessor;
    this.createSubjectAreaUnitGIResponseProcessor = createSubjectAreaUnitGIResponseProcessor;
  }

  @Override
  public void route() throws Exception {
    soap(DIRECT_CREATE_SUBJECT_AREA_UNIT)
        .request(createSubjectAreaUnitGIProcessor)
        .unmarshal(CreateSubjectAreaUnitAnfrageDTO.class)
        .response(createSubjectAreaUnitGIResponseProcessor);
  }
}
