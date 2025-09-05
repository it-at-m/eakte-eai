package de.muenchen.dms.akte.anlegen.complete;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompleteFileRequestDTO {
  @Schema(
      description = "Parameter, die die komplette Akte beschreiben",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private CreateCompleteFileBodyParams params;

  @ArraySchema(
      arraySchema =
          @Schema(description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument"),
      items = @Schema(type = "string", format = "binary"),
      schema = @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED))
  private List<File> giattachmenttype;
}
