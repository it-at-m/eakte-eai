/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common.processor;

import static de.muenchen.dms.common.route.exception.ExceptionResponses.createDmsErrorResponse;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import de.muenchen.dms.common.datasource.AttachmentDataSource;
import de.muenchen.dms.common.model.DmsDeveloperException;
import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.util.JacksonData;
import jakarta.activation.DataHandler;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

public abstract class AbstractResponseProcessor implements Processor {

  private static final ObjectWriter JSON_WRITER =
      JacksonData.erzeugeObjectMapper().writerWithDefaultPrettyPrinter();

  protected static void setResult(
      final Exchange exchange, final int responseCode, final Object result)
      throws JsonProcessingException {
    setHeaders(exchange, responseCode);
    // set body
    exchange.getIn().setBody(JSON_WRITER.writeValueAsString(result));
  }

  protected static void setNoContentResult(final Exchange exchange) {
    setHeaders(exchange, HttpStatus.NO_CONTENT.value());
  }

  protected static void addAttachment(
      final Exchange exchange, LHMBAI151700GIAttachmentType attachment) {
    var message = getMessage(exchange);
    checkMessage(message);
    checkAttachment(attachment);
    message.addAttachment(
        getAttachmentName(attachment), new DataHandler(new AttachmentDataSource(attachment)));
  }

  protected static void setFileAttachmentAsBody(
      final Exchange exchange,
      final int status,
      final String errorMessage,
      LHMBAI151700GIAttachmentType attachment)
      throws JsonProcessingException {
    if (status != 0) {
      setResult(
          exchange, HttpStatus.BAD_REQUEST.value(), createDmsErrorResponse(status, errorMessage));
    } else {
      exchange.getIn().setBody(attachment.getLHMBAI151700Filecontent());
      exchange.getIn().removeHeaders("*");
      exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, HttpStatus.OK.value());
      // set content type
      exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_OCTET_STREAM_VALUE);
      String dispostionValue =
          "attachment;filename="
              + attachment.getLHMBAI151700Filename()
              + "."
              + attachment.getLHMBAI151700Fileextension();
      exchange.getIn().setHeader("Content-Disposition", dispostionValue);
    }
  }

  private static String getAttachmentName(LHMBAI151700GIAttachmentType attachment) {
    StringBuilder sb = new StringBuilder();

    if (attachment.getLHMBAI151700Filename() != null) {
      sb.append(attachment.getLHMBAI151700Filename());
    }

    if (attachment.getLHMBAI151700Fileextension() != null) {
      sb.append(".");
      sb.append(attachment.getLHMBAI151700Fileextension());
    }

    return sb.toString();
  }

  private static AttachmentMessage getMessage(final Exchange exchange) {
    return exchange.getMessage(AttachmentMessage.class);
  }

  private static void checkMessage(AttachmentMessage message) {
    if (message == null) {
      throw new DmsDeveloperException(
          "Es können können keine Anhänge zur gegebenen Nachricht zurückgegeben werden.");
    }
  }

  private static void checkAttachment(LHMBAI151700GIAttachmentType attachment) {
    if (attachment == null) {
      throw new DmsDeveloperException("Es wurde kein Attachment angegeben.");
    }
  }

  private static void setHeaders(final Exchange exchange, final int responseCode) {
    // remove old headers
    exchange.getIn().removeHeaders("*");
    exchange.getIn().setHeader(Exchange.HTTP_RESPONSE_CODE, responseCode);
    // set content type
    exchange.getIn().setHeader(Exchange.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
  }

  protected static void handleResponse(
      Exchange exchange,
      final int status,
      final String errormessage,
      final String objname,
      final String objid)
      throws JsonProcessingException {
    if (status != 0) {
      setResult(
          exchange, HttpStatus.BAD_REQUEST.value(), createDmsErrorResponse(status, errormessage));
    } else {
      final DmsObjektResponse objektResponse =
          DmsObjektResponse.builder().objname(objname).objid(objid).build();

      setResult(exchange, HttpStatus.OK.value(), objektResponse);
    }
  }

  protected static void checkResponse(
      Exchange exchange, final int status, final String errorMessage, final Object result)
      throws JsonProcessingException {
    if (status != 0) {
      setResult(
          exchange, HttpStatus.BAD_REQUEST.value(), createDmsErrorResponse(status, errorMessage));
    } else {
      setResult(exchange, HttpStatus.OK.value(), result);
    }
  }

  protected static void checkResponseNoContent(
      Exchange exchange, final int status, final String errorMessage)
      throws JsonProcessingException {
    if (status != 0) {
      setResult(
          exchange, HttpStatus.BAD_REQUEST.value(), createDmsErrorResponse(status, errorMessage));
    } else {
      setNoContentResult(exchange);
    }
  }
}
