package de.muenchen.dms.search.apentry;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_SEARCH_APENTRY;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SearchApentryRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/apentries")
        .post("/search")
        .id(ROUTE_ID_SEARCH_APENTRY)
        .description("Suche nach Aktenplaneinträgen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(SearchApentryDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                SearchApentryResponseDTO.class))
        .to(RouteConstants.DIRECT_SEARCH_APENTRY);
  }
}
