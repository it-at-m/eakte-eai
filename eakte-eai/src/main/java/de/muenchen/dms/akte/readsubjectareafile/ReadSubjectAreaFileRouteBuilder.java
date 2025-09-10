package de.muenchen.dms.akte.readsubjectareafile;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_SUBJECT_AREA_FILE;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadSubjectAreaFileRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/subjectAreaFileObjects")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_SUBJECT_AREA_FILE)
        .description("Liste von Vorgängen auslesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .params(RestOperationDefinitionFactory.createObjectHeaderReadSubject())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadSubjectAreaFileAntwortDTO.class))
        .to(RouteConstants.DIRECT_READ_SUBJECT_AREA_FILE);
  }
}
