package de.muenchen.dms.test;

import de.muenchen.dms.common.model.DMSContainerBase;
import de.muenchen.dms.common.route.QueryProperties;
import jakarta.activation.DataHandler;
import jakarta.mail.MessagingException;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.camel.support.DefaultExchange;
import org.apache.hc.client5.http.entity.mime.FileBody;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;

/** Diese Klasse stellt statische Methoden für den Test mit Exchange-Objekten bereit. */
public class TestExchanges {
  static final String NUTZER = "mkf";
  static final String ANWENDUNG = "SHW";
  static final String ORGANISATIONSEINHEIT = "COO.4321.1234.1.1234567";
  static final String STELLE = "DocumentManager";

  private TestExchanges() {}

  public static <T extends DMSContainerBase> Exchange erzeuge(
      CamelContext ctx, T dto, Class<T> dtoClazz, Class<?> soapFunctionClass) {
    return erzeuge(ctx, dto, dtoClazz, soapFunctionClass, null);
  }

  public static <T> Exchange erzeuge(
      CamelContext ctx, T dto, Class<T> dtoClazz, Class<?> soapFunctionClass) {
    return erzeuge(ctx, dto, dtoClazz, soapFunctionClass, null);
  }

  public static void setzeHeader(Exchange exchange, Class<?> soapFunctionClass) {
    Message nachricht = exchange.getIn();

    nachricht.removeHeaders("*");
    nachricht.setHeader(CxfConstants.OPERATION_NAME, soapFunctionClass.getSimpleName());

    exchange.setProperty(QueryProperties.PROPERTY_NUTZER, NUTZER);
    exchange.setProperty(QueryProperties.PROPERTY_ANWENDUNG, ANWENDUNG);
    exchange.setProperty(QueryProperties.PROPERTY_ORGANISATIONSEINHEIT, ORGANISATIONSEINHEIT);
    exchange.setProperty(QueryProperties.PROPERTY_ROLLE, STELLE);
  }

  public static Exchange erzeuge(CamelContext ctx) {
    return new DefaultExchange(ctx);
  }

  public static <T extends DMSContainerBase> Exchange erzeuge(
      CamelContext ctx,
      T dto,
      Class<T> dtoClazz,
      Class<?> soapFunctionClass,
      Map<String, DataHandler> anhaenge) {
    Exchange exchange = erzeuge(ctx);

    Message nachricht = exchange.getIn();
    setzeHeader(exchange, soapFunctionClass);
    nachricht.setBody(dto, dtoClazz);

    if (anhaenge != null) {
      exchange.getMessage(AttachmentMessage.class).setAttachments(anhaenge);
    }

    return exchange;
  }

  public static <T> Exchange erzeuge(
      CamelContext ctx,
      T dto,
      Class<T> dtoClazz,
      Class<?> soapFunctionClass,
      Map<String, DataHandler> anhaenge) {
    Exchange exchange = erzeuge(ctx);

    Message nachricht = exchange.getIn();
    setzeHeader(exchange, soapFunctionClass);
    nachricht.setBody(dto, dtoClazz);

    if (anhaenge != null) {
      exchange.getMessage(AttachmentMessage.class).setAttachments(anhaenge);
    }

    return exchange;
  }

  public static Exchange erzeuge(CamelContext ctx, Class<?> soapFunctionClass, File anhang)
      throws IOException, MessagingException {
    Exchange exchange = erzeuge(ctx);
    Message nachricht = exchange.getIn();

    MultipartEntityBuilder entityBuilder = MultipartEntityBuilder.create();
    entityBuilder.addPart("file", new FileBody(anhang));
    ByteArrayOutputStream os;
    try (var entity = entityBuilder.build()) {

      // as setBody with entity.getContent() does not work because
      // of buffer limits, we need to manually write the stream to the body
      // important to know, that the boundary is contained in the stream and also added to content
      // therefore the content comparison in the test does not work
      os = new ByteArrayOutputStream();
      entity.writeTo(os);
    }
    os.flush();

    nachricht.setBody(new ByteArrayInputStream(os.toByteArray()));

    setzeHeader(exchange, soapFunctionClass);

    return exchange;
  }

  public static String getNutzer() {
    return NUTZER;
  }

  public static String getAnwendung() {
    return ANWENDUNG;
  }

  public static String getStelle() {
    return STELLE;
  }

  public static String getOrganisationseinheit() {
    return ORGANISATIONSEINHEIT;
  }
}
