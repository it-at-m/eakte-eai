/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.io.File;
import java.util.List;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class CreateOutgoingRequestBodyDTO {

  @Schema(
      description = "Parameter, die den Ausgang spezifizieren",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private CreateOutgoingAnfrageDTO params;

  @ArraySchema(
      arraySchema =
          @Schema(
              description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument."),
      items = @Schema(type = "string", format = "binary"),
      schema = @Schema(requiredMode = RequiredMode.NOT_REQUIRED))
  private List<File> giattachmenttype;
}
