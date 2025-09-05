/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common;

import org.apache.camel.Processor;

public interface DmsResponseFluentApi {

  interface DmsRequestApi {
    DmsResponseApi request(Processor processor);
  }

  interface DmsResponseApi {
    DmsResponseApi unmarshal(Class<?> clazz);

    DmsResponseApi mock(Processor processor);

    void response(Processor processor);
  }

  interface DmsResponseFluentApiDone {}
}
