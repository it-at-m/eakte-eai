/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common.processor;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import de.muenchen.dms.common.model.Attachment;
import de.muenchen.dms.common.route.QueryProperties;
import jakarta.activation.DataHandler;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.attachment.AttachmentMessage;
import org.apache.camel.component.cxf.common.message.CxfConstants;
import org.apache.commons.io.IOUtils;

@Slf4j
public abstract class AbstractDMSSoapProcessor implements Processor {

  protected ObjectFactory objectFactory = new ObjectFactory();

  protected static void setParameters(
      final Exchange exchange, final Object parameters, final Class<?> t) {
    // remove all headers.
    exchange.getIn().removeHeaders("*");
    exchange.getIn().setHeader(CxfConstants.OPERATION_NAME, t.getSimpleName());
    exchange.getIn().setBody(parameters);
  }

  protected static String getNutzer(Exchange exchange) {
    return exchange.getProperty(QueryProperties.PROPERTY_NUTZER, String.class);
  }

  protected static String getOrganisationseinheit(Exchange exchange) {
    return exchange.getProperty(QueryProperties.PROPERTY_ORGANISATIONSEINHEIT, String.class);
  }

  protected static String getRolle(Exchange exchange) {
    return exchange.getProperty(QueryProperties.PROPERTY_ROLLE, String.class);
  }

  protected static String getAnwendung(Exchange exchange) {
    return exchange.getProperty(QueryProperties.PROPERTY_ANWENDUNG, String.class);
  }

  /**
   * Extrahiert mehrere Anhänge aus einem Multipart-Request.
   *
   * <p>Diese Methode liest alle Anhänge aus dem übergebenen Exchange-Objekt und gibt sie als eine
   * Liste von Attachment-Datenstrukturen zurück. Jedes Attachment enthält Informationen wie den
   * Namen, die Erweiterung und den Inhalt.
   *
   * @param exchange Das Exchange-Objekt, das mehrere Anhänge enthält.
   * @return Eine Liste von Attachment-Objekten, die die extrahierten Anhänge repräsentieren.
   */
  protected static List<Attachment> createAttachments(final Exchange exchange) {
    final AttachmentMessage attachmentMessage = exchange.getMessage(AttachmentMessage.class);
    final Map<String, DataHandler> attachments =
        attachmentMessage != null ? attachmentMessage.getAttachments() : null;

    if (attachments == null || attachmentMessage.getAttachmentNames() == null) {
      return List.of();
    }

    return attachmentMessage.getAttachmentNames().stream()
        .map(attachments::get)
        .map(AbstractDMSSoapProcessor::createAttachmentFromDataHandler)
        .toList();
  }

  /**
   * Extrahiert einen einzelnen Anhang aus einem Multipart-Request.
   *
   * <p>Diese Methode liest den ersten Anhang aus dem übergebenen Exchange-Objekt und gibt ihn als
   * eine Attachment-Datenstruktur zurück. Wenn kein Anhang vorhanden ist, wird ein leeres
   * Attachment zurückgegeben.
   *
   * @param exchange Das Exchange-Objekt, das einen Anhang enthält.
   * @return Ein Attachment-Objekt, das den extrahierten Anhang repräsentiert.
   */
  protected static Attachment createAttachment(final Exchange exchange) {
    final AttachmentMessage attachmentMessage = exchange.getMessage(AttachmentMessage.class);
    if (attachmentMessage == null
        || attachmentMessage.getAttachmentNames() == null
        || attachmentMessage.getAttachmentNames().size() != 1) {
      return new Attachment();
    }

    final String attachmentName = attachmentMessage.getAttachmentNames().iterator().next();
    final DataHandler dataHandler = attachmentMessage.getAttachments().get(attachmentName);
    return createAttachmentFromDataHandler(dataHandler);
  }

  /**
   * Erstellt ein Attachment-Objekt aus einem DataHandler.
   *
   * <p>Diese Methode konvertiert einen DataHandler in ein Attachment-Objekt, indem sie den
   * Dateinamen dekodiert, die Dateierweiterung extrahiert und den Inhalt als Byte-Array liest.
   *
   * @param dataHandler Der DataHandler, der die Anhänge enthält.
   * @return Ein Attachment-Objekt, das die Informationen des DataHandlers repräsentiert.
   */
  private static Attachment createAttachmentFromDataHandler(DataHandler dataHandler) {
    Attachment file = new Attachment();
    final String filename = dataHandler.getName();

    String decodedFilename;
    try {
      decodedFilename =
          new String(filename.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
    } catch (Exception e) {
      log.error("Error decoding filename");
      decodedFilename = filename;
    }

    int index = decodedFilename.lastIndexOf('.');
    file.setFileName(index > -1 ? decodedFilename.substring(0, index) : decodedFilename);
    file.setFileExtention(index > -1 ? decodedFilename.substring(index + 1) : null);

    try {
      file.setContent(IOUtils.toByteArray(dataHandler.getInputStream()));
    } catch (IOException e) {
      log.error("Error reading document '{}':\n{}", decodedFilename, e.getMessage());
    }
    return file;
  }

  /**
   * Gefundene Attachments in das Fabasoft SOAP format umwandeln.
   *
   * @param files Liste an Dateeien mit Inhalt, name etc..
   */
  protected ArrayOfLHMBAI151700GIAttachmentType createLHMBAI151700GIAttachments(
      final List<Attachment> files) {
    ArrayOfLHMBAI151700GIAttachmentType attachemntType = null;
    if (!files.isEmpty()) {
      attachemntType = objectFactory.createArrayOfLHMBAI151700GIAttachmentType();
      attachemntType
          .getLHMBAI151700GIAttachmentType()
          .addAll(
              files.stream()
                  .map(
                      schriftstueck -> {
                        LHMBAI151700GIAttachmentType attachment =
                            objectFactory.createLHMBAI151700GIAttachmentType(); // document
                        attachment.setLHMBAI151700Filecontent(schriftstueck.getContent());
                        attachment.setLHMBAI151700Filename(schriftstueck.getFileName());
                        attachment.setLHMBAI151700Fileextension(schriftstueck.getFileExtention());
                        return attachment;
                      })
                  .toList());
    }
    return attachemntType;
  }

  protected ArrayOfLHMBAI151700GIAttachmentType createLHMBAI151700GIAttachmentsForCOAITI(
      final List<Attachment> files, final List<List<String>> filesubj) {
    ArrayOfLHMBAI151700GIAttachmentType attachemntType = null;
    if (!files.isEmpty()) {
      attachemntType = objectFactory.createArrayOfLHMBAI151700GIAttachmentType();

      attachemntType
          .getLHMBAI151700GIAttachmentType()
          .addAll(
              files.stream()
                  .map(
                      schriftstueck -> {
                        LHMBAI151700GIAttachmentType attachment =
                            objectFactory.createLHMBAI151700GIAttachmentType(); // document
                        attachment.setLHMBAI151700Filecontent(schriftstueck.getContent());
                        attachment.setLHMBAI151700Filename(schriftstueck.getFileName());
                        attachment.setLHMBAI151700Fileextension(schriftstueck.getFileExtention());

                        int index = files.indexOf(schriftstueck);

                        if (filesubj != null) {
                          List<String> subjList =
                              index < filesubj.size() ? filesubj.get(index) : null;
                          ArrayOfstring arrayOfstring = new ArrayOfstring();

                          if (subjList != null && !subjList.isEmpty()) {
                            arrayOfstring.getString().addAll(subjList);
                          }

                          attachment.setLHMBAI151700Filesubj(arrayOfstring);
                        }

                        return attachment;
                      })
                  .toList());
    }
    return attachemntType;
  }

  /**
   * Gefundene Attachments in das Fabasoft SOAP format umwandeln.
   *
   * @param file Dateien mit Inhalt, Name etc..
   * @return LHMBAI151700GIAttachmentType
   */
  protected LHMBAI151700GIAttachmentType createLHMBAI151700GIAttachment(final Attachment file) {
    LHMBAI151700GIAttachmentType attachment = objectFactory.createLHMBAI151700GIAttachmentType();
    if (file != null) {
      attachment.setLHMBAI151700Filecontent(file.getContent());
      attachment.setLHMBAI151700Filename(file.getFileName());
      attachment.setLHMBAI151700Fileextension(file.getFileExtention());
    }
    return attachment;
  }

  protected LHMBAI151700GIAttachmentType createLHMBAI151700GIAttachmentWithFilesubj(
      final Attachment file, final List<String> filesubj) {
    LHMBAI151700GIAttachmentType attachment = objectFactory.createLHMBAI151700GIAttachmentType();
    if (file != null) {
      attachment.setLHMBAI151700Filecontent(file.getContent());
      attachment.setLHMBAI151700Filename(file.getFileName());
      attachment.setLHMBAI151700Fileextension(file.getFileExtention());

      if (filesubj != null) {
        ArrayOfstring arrayOfstring = new ArrayOfstring();

        arrayOfstring.getString().addAll(filesubj);

        attachment.setLHMBAI151700Filesubj(arrayOfstring);
      }
    }
    return attachment;
  }
}
