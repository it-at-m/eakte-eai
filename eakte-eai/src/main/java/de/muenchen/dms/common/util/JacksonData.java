/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import de.muenchen.dms.common.dataformat.CustomMimeMultipartDataFormat;
import java.time.OffsetDateTime;
import java.util.GregorianCalendar;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.camel.component.jackson.JacksonDataFormat;

public interface JacksonData {
  /**
   * Lege einen ObjectMapper an, der zwischen String-Repräsentation und JSON-Format umwandeln kann
   * (in beide Richtungen).
   *
   * @return ein ObjectMapper
   */
  static ObjectMapper erzeugeObjectMapper() {
    return new ObjectMapper()
        .registerModule(new JavaTimeModule())
        .disable(SerializationFeature.WRITE_DURATIONS_AS_TIMESTAMPS)
        .disable(SerializationFeature.WRITE_DATE_KEYS_AS_TIMESTAMPS)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
        .disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);
  }

  static JacksonDataFormat getJacksonDataFormat(Class<?> cl) {
    final JacksonDataFormat format = new JacksonDataFormat(cl);
    format.setModuleClassNames("com.fasterxml.jackson.datatype.jsr310.JavaTimeModule");
    return format;
  }

  static CustomMimeMultipartDataFormat getMimeMultipartDataFormat() {
    return new CustomMimeMultipartDataFormat(false, "params");
  }

  static XMLGregorianCalendar toXMLGregorianCalendar(final OffsetDateTime time)
      throws DatatypeConfigurationException {
    if (time == null) {
      return null;
    }

    return DatatypeFactory.newInstance()
        .newXMLGregorianCalendar(GregorianCalendar.from(time.toZonedDateTime()));
  }
}
