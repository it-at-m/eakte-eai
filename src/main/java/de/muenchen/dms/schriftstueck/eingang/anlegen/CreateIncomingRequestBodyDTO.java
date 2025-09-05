package de.muenchen.dms.schriftstueck.eingang.anlegen;

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
public class CreateIncomingRequestBodyDTO {

  @Schema(
      description = "Parameter, die den Eingang spezifizieren",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private CreateIncomingBasisAnfrageDTO params;

  @ArraySchema(
      arraySchema =
          @Schema(
              description =
                  """
        Schriftstücke
        Schriftstücke können über diesen Aufruf nicht geändert bzw. ersetzt werden.
        Neue Schriftstücke können bei Bedarf (zum Beispiel zum nachträglichen Hinzufügen
        von Anhängen vor dem Postversand) über diesen Aufruf hinzugefügt werden.
"""),
      items = @Schema(type = "string", format = "binary"),
      schema = @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED))
  private List<File> giattachmenttype;
}
