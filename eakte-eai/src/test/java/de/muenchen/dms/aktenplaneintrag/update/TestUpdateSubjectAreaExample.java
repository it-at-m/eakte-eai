/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */

package de.muenchen.dms.aktenplaneintrag.update;

// import javax.xml.datatype.DatatypeConfigurationException;

public class TestUpdateSubjectAreaExample {

  private TestUpdateSubjectAreaExample() {}

  static UpdateSubjectAreaRequestDTO erzeugeAntwort() {
    return UpdateSubjectAreaRequestDTO.builder().subjareascope("Betreffseinheit").build();
  }
}
