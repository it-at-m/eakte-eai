package de.muenchen.dms.search.procedure;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SearchProcedureRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/procedures")
        .post("/search")
        .id(RouteConstants.ROUTE_ID_SEARCH_PROCEDURE)
        .description("Suche nach Vorgängen")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(SearchProcedureRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                SearchProcedureResponseDTO.class))
        .to(RouteConstants.DIRECT_SEARCH_PROCEDURE);
  }
}
