/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Enthält die Informationen der REST-Antwort nach Anlegen eines Eingangsdokuments */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class CreateIncomingAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des erzeugten Objekts")
  private String objid;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objektname des erzeugten Objekts")
  private String objname;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
            Auflistung aller Objekte, auf die der Suchstring zutrifft.
            Rückgabe der Eigenschaften, die im Datentyp GIObjectType definiert sind.
            """,
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static CreateIncomingAntwortDTO sample() {
    CreateIncomingAntwortDTO result =
        CreateIncomingAntwortDTO.builder()
            .giobjecttype(
                Arrays.asList(
                    Objektreferenz.builder().objaddress("COO.1").objname("Schreibstück 1").build(),
                    Objektreferenz.builder().objaddress("COO.2").objname("Schreibstück 2").build(),
                    Objektreferenz.builder().objaddress("COO.3").objname("Schreibstück 3").build()))
            .build();

    result.setObjname("Dokument 1");
    result.setObjid("COO.1");

    return result;
  }
}
