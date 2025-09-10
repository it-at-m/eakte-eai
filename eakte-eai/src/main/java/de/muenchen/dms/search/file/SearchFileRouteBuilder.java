package de.muenchen.dms.search.file;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SearchFileRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/files")
        .post("/search")
        .id(RouteConstants.ROUTE_ID_SEARCH_FILE)
        .description("Suche nach Akten")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(SearchFileRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                SearchFileResponseDTO.class))
        .to(RouteConstants.DIRECT_SEARCH_FILE);
  }
}
