package de.muenchen.dms.aktenplaneintrag.anlegen;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_CREATE_SUBJECT_AREA_UNIT;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_CREATE_SUBJECT_AREA_UNIT;

import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateSubjectAreaUnitRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/subjectAreaUnits")
        .post()
        .id(ROUTE_ID_CREATE_SUBJECT_AREA_UNIT)
        .description("Betreffseinheit anlegen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateSubjectAreaUnitAnfrageDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(DmsObjektResponse.class))
        .to(DIRECT_CREATE_SUBJECT_AREA_UNIT);
  }
}
