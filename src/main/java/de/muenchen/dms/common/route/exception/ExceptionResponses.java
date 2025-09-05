/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception;

import de.muenchen.dms.common.model.DmsErrorResponse;
import de.muenchen.dms.common.model.DmsErrorSource;
import de.muenchen.dms.common.model.InternalErrors;
import java.util.Optional;
import org.apache.camel.Exchange;
import org.springframework.http.HttpStatus;

public class ExceptionResponses {
  private ExceptionResponses() {}

  public static void setResponse(
      Exchange exchange, int responseCode, String responseText, DmsErrorResponse responseBody) {
    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, Integer.valueOf(responseCode));
    exchange
        .getIn()
        .setHeader(
            Exchange.HTTP_RESPONSE_TEXT,
            Optional.ofNullable(responseText)
                .orElseGet(() -> HttpStatus.valueOf(responseCode).getReasonPhrase()));
    exchange.getIn().setBody(responseBody);
  }

  public static DmsErrorResponse createInternalErrorResponse(
      InternalErrors errorCode, String errorMessage) {
    return DmsErrorResponse.builder()
        .fehlerQuelle(DmsErrorSource.INTERN)
        .status(errorCode.getErrorCode())
        .text(errorMessage)
        .build();
  }

  public static DmsErrorResponse createDmsErrorResponse(int code, String errorMessage) {
    return DmsErrorResponse.builder()
        .fehlerQuelle(DmsErrorSource.DMS)
        .status(code)
        .text(errorMessage)
        .build();
  }

  public static String getMessageFrom(Throwable throwable) {
    if (throwable == null) {
      return null;
    }

    if (throwable.getLocalizedMessage() == null) {
      return throwable.getMessage() == null ? "" : throwable.getMessage();
    }
    return throwable.getLocalizedMessage();
  }
}
