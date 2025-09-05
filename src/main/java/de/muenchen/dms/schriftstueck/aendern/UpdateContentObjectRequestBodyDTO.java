package de.muenchen.dms.schriftstueck.aendern;

import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode
public class UpdateContentObjectRequestBodyDTO {

  @Schema() private UpdateContentObjectDTO params;

  @Schema(
      type = "string",
      format = "binary",
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument")
  private File giattachmenttype;
}
