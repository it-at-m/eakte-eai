/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.datasource;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import jakarta.activation.DataSource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.springframework.http.MediaType;

/**
 * Diese Klasse stellte eine Datenquelle z. B. für Anhänge zu mehrteiligen Nachrichten dar. Benötigt
 * wird ein Attachment-Objekt aus einer SOAP-Nachricht, aus der die Inhalte für die Datenquelle
 * gezogen werden.
 */
public class AttachmentDataSource implements DataSource {
  private final LHMBAI151700GIAttachmentType attachmentType;

  public AttachmentDataSource(LHMBAI151700GIAttachmentType attachmentType) {
    this.attachmentType = attachmentType;
  }

  @Override
  public InputStream getInputStream() throws IOException {
    return new ByteArrayInputStream(attachmentType.getLHMBAI151700Filecontent());
  }

  @Override
  public OutputStream getOutputStream() throws IOException {
    throw new UnsupportedOperationException();
  }

  @Override
  public String getContentType() {
    return MediaType.APPLICATION_OCTET_STREAM_VALUE;
  }

  @Override
  public String getName() {
    StringBuilder sb = new StringBuilder();

    if (attachmentType.getLHMBAI151700Filename() != null) {
      sb.append(attachmentType.getLHMBAI151700Filename());
    }

    if (attachmentType.getLHMBAI151700Fileextension() != null) {
      sb.append(attachmentType.getLHMBAI151700Fileextension());
    }

    return sb.toString();
  }
}
