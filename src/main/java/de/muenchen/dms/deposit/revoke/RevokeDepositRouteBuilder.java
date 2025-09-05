package de.muenchen.dms.deposit.revoke;

import static de.muenchen.dms.common.route.RouteConstants.BASIC_AUTH_SECURITY_DEFINITION;
import static de.muenchen.dms.common.route.RouteConstants.DIRECT_REVOKE_DEPOSIT;
import static de.muenchen.dms.common.route.RouteConstants.PATH_PARAM_OBJADDRESS;
import static de.muenchen.dms.common.route.RouteConstants.ROUTE_ID_REVOKE_DEPOSIT;

import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class RevokeDepositRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/revokeDepositObjects")
        .put("/{" + PATH_PARAM_OBJADDRESS + "}")
        .id(ROUTE_ID_REVOKE_DEPOSIT)
        .description("z.A. aufheben")
        .security(BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.APPLICATION_JSON_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(DmsObjektResponse.class))
        .to(DIRECT_REVOKE_DEPOSIT);
  }
}
