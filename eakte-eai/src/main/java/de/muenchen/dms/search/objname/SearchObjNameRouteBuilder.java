package de.muenchen.dms.search.objname;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_SEARCH_OBJ_NAME;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_SEARCH_OBJ_NAME;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class SearchObjNameRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/searchObjNames")
        .post("/search")
        .id(ROUTE_ID_SEARCH_OBJ_NAME)
        .description("Suche nach Objektnamen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(SearchObjNameAnfrageDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                SearchObjNameAntwortDTO.class))
        .to(DIRECT_SEARCH_OBJ_NAME);
  }
}
