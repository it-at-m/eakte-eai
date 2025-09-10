/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

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

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public final class CreateOutgoingAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des erzeugten Ausgangs.",
      example = "COO.1.2301.1.1042465")
  private String objid;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objektname des angelegten Ausgangdokuments",
      example =
          "Ausgangsschreiben zum Antrag auf Baugenehmigung Firma Mustermann (0010 A20 011-4-0006-0006)")
  private String objname;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument.",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static CreateOutgoingAntwortDTO sample() {
    CreateOutgoingAntwortDTO result =
        CreateOutgoingAntwortDTO.builder()
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
