package de.muenchen.dms.schriftstueck.anlegen;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CreateContentObjectRequestBodyDTO {

  @Schema(
      description = "Parameter, die das  Schriftstück beschreiben",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private CreateContentObjectAnfrageDTO params;

  @ArraySchema(
      arraySchema =
          @Schema(
              description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument."),
      items = @Schema(type = "string", format = "binary"),
      schema = @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED))
  private List<File> giattachmenttype;
}
