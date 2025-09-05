package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Schema;
import java.io.File;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class CreateObjectAndImportToInboxRequestDTO {

  @Schema(
      description = "Parameter, die den Postkorb beschreiben",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private CreateObjectAndImportToInboxDTO params;

  @ArraySchema(
      arraySchema =
          @Schema(
              description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument."),
      items = @Schema(type = "string", format = "binary"),
      schema = @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED))
  private List<File> giattachmenttype;
}
