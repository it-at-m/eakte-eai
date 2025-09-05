package de.muenchen.dms.akte.readapentry;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_READ_APENTRY;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_READ_APENTRY;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class ReadApentryRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/apentries")
        .get("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_READ_APENTRY)
        .description("Untergeordnete Aktenplaneinträge auslesen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                ReadApentryAntwortDTO.class))
        .to(DIRECT_READ_APENTRY);
  }
}
