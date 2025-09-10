package de.muenchen.dms.disposalschedule.aendern;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_DISPOSAL_SCHEDULE_DATE;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_DISPOSAL_SCHEDULE_DATE;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SetDisposalScheduleDateRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {

    rest("/disposalScheduleDates")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_DISPOSAL_SCHEDULE_DATE)
        .description("Aufbewahrungsdatum setzen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(SetDisposalScheduleDateAnfrageDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                SetDisposalScheduleDateAntwortDTO.class))
        .to(DIRECT_DISPOSAL_SCHEDULE_DATE);
  }
}
