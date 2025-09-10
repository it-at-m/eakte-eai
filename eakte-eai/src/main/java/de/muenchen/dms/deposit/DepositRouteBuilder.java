package de.muenchen.dms.deposit;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_ROUTE_DEPOSIT_OBJECT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_DEPOSIT_OBJECT;

import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class DepositRouteBuilder extends DmsRouteBuilder {

  @Override
  public void route() throws Exception {
    rest("/depositObjects")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_DEPOSIT_OBJECT)
        .description("z.A. setzen")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(DmsObjektResponse.class))
        .to(DIRECT_ROUTE_DEPOSIT_OBJECT);
  }
}
