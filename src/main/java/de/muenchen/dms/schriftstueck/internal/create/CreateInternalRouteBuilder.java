package de.muenchen.dms.schriftstueck.internal.create;

import de.muenchen.dms.common.route.DmsRouteBuilder;
import de.muenchen.dms.common.route.RestOperationDefinitionFactory;
import de.muenchen.dms.common.route.RouteConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class CreateInternalRouteBuilder extends DmsRouteBuilder {
  @Override
  public void route() throws Exception {
    rest("/internal")
        .post()
        .id(RouteConstants.ROUTE_ID_CREATE_INTERNAL)
        .description("Anlegen eines internen Dokuments")
        .security(RouteConstants.BASIC_AUTH_SECURITY_DEFINITION)
        .consumes(MediaType.MULTIPART_FORM_DATA_VALUE)
        .produces(MediaType.APPLICATION_JSON_VALUE)
        .type(CreateInternatRequestDTO.class)
        .params(RestOperationDefinitionFactory.createAuthentificationHeader())
        .responseMessages(
            RestOperationDefinitionFactory.createBaseResponseMsgDefinition(
                CreateInternalResponseDTO.class))
        .to(RouteConstants.DIRECT_CREATE_INTERNAL);
  }
}
