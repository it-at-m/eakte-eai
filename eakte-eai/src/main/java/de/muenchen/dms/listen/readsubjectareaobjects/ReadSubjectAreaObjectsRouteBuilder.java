package de.muenchen.dms.listen.readsubjectareaobjects;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_SUBJECT_AREA_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_SUBJECT_AREA_OBJECT;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaObjectsRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/subjectAreas")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_SUBJECT_AREA_OBJECT)
        .description("Liste von Sachakten auslesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadSubjectAreaObjectsAntwortDTO.class))
        .to(DIRECT_READ_SUBJECT_AREA_OBJECT);
  }
}
