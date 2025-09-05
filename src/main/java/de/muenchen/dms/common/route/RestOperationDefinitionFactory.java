/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route;

import static org.apache.camel.model.rest.RestParamType.header;

import de.muenchen.dms.common.model.DmsErrorResponse;
import java.util.Arrays;
import java.util.List;
import org.apache.camel.model.rest.ParamDefinition;
import org.apache.camel.model.rest.ResponseMessageDefinition;
import org.springframework.http.HttpStatus;

public final class RestOperationDefinitionFactory {
  private static final String DATATYPE_STRING = "string";
  private static final String DATATYPE_INTEGER = "integer";
  public static final String BAD_CREDENTIALS_MSG = "Falscher oder fehlender technischer Nutzer.";

  private RestOperationDefinitionFactory() {
    /* hide c'tor */
  }

  public static List<ResponseMessageDefinition> createBaseResponseMsgDefinition(Class<?> okType) {
    return Arrays.asList(
        new ResponseMessageDefinition()
            .code(HttpStatus.OK.value())
            .message("OK")
            .responseModel(okType),
        new ResponseMessageDefinition()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(BAD_CREDENTIALS_MSG),
        new ResponseMessageDefinition()
            .code(HttpStatus.BAD_REQUEST.value())
            .message("Aufruf ans DMS ist gescheitert. Im Body sind Details enthalten."),
        new ResponseMessageDefinition()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Ein unerwarteter Fehler innerhalb der EAI ist aufgetreten."));
  }

  public static List<ResponseMessageDefinition> createBaseResponseMsgDefinition() {
    return Arrays.asList(
        new ResponseMessageDefinition().code(HttpStatus.NO_CONTENT.value()),
        new ResponseMessageDefinition()
            .code(HttpStatus.UNAUTHORIZED.value())
            .message(BAD_CREDENTIALS_MSG),
        new ResponseMessageDefinition()
            .code(HttpStatus.BAD_REQUEST.value())
            .message("Aufruf ans DMS ist gescheitert. Im Body sind Details enthalten.")
            .responseModel(DmsErrorResponse.class),
        new ResponseMessageDefinition()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message("Ein unerwarteter Fehler innerhalb der EAI ist aufgetreten."));
  }

  public static List<ParamDefinition> createAuthentificationHeader() {
    return Arrays.asList(
        new ParamDefinition()
            .name(RouteConstants.HEADER_PARAM_BUSINESSAPP)
            .type(header)
            .dataType(DATATYPE_STRING)
            .required(false)
            .example("SWH")
            .description("Aufrufende Fachanwendung"),
        new ParamDefinition()
            .name(RouteConstants.HEADER_PARAM_USERLOGIN)
            .type(header)
            .dataType(DATATYPE_STRING)
            .required(false)
            .description(
                "Übergabe des Benutzer-Logins, von deren Berechtigung die Aktion durchgeführt werden soll.")
            .example("max.mustermann"),
        new ParamDefinition()
            .name(RouteConstants.HEADER_PARAM_JOBOE)
            .type(header)
            .dataType(DATATYPE_STRING)
            .required(false)
            .description("COO-ID der Organisationseinheit des Nutzers")
            .example("COO.1.2300.1.847"),
        new ParamDefinition()
            .name(RouteConstants.HEADER_PARAM_JOBPOSITION)
            .type(header)
            .dataType(DATATYPE_STRING)
            .required(false)
            .description("Rolle, in der der Nutzer handelt")
            .example("DocumentManager"));
  }

  public static List<ParamDefinition> createObjectHeaderReadSubject() {

    return Arrays.asList(
        new ParamDefinition()
            .name(QueryProperties.PROPERTY_DOCSTATE)
            .type(header)
            .dataType(DATATYPE_INTEGER)
            .required(Boolean.FALSE)
            .example("12")
            .description("docstate Nummer"),
        new ParamDefinition()
            .name(QueryProperties.PROPERTY_BOSTATE)
            .type(header)
            .dataType(DATATYPE_STRING)
            .required(Boolean.FALSE)
            .description("bostate")
            .example("COO.1.2301.1.679"));
  }
}
