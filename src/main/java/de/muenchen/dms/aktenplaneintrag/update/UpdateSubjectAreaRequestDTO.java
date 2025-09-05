/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2025
 */

package de.muenchen.dms.aktenplaneintrag.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateSubjectAreaRequestDTO {
  @Builder
  public UpdateSubjectAreaRequestDTO(String subjareascope) {
    this.subjareascope = subjareascope;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          """
                    Der neue Aktenplaneintragstyp.
                    Folgende Werte sind möglich:
                    • Hauptgruppe
                    • Obergruppe
                    • Gruppe
                    • Untergruppe
                    • Betreffseinheit.
                    Eine änderung darf nur erfolgen wenn aktenplaneintragstyp leer ist.
                   """,
      example = "\"subjareascope\": \"Betreffseinheit\"")
  private String subjareascope;
}
