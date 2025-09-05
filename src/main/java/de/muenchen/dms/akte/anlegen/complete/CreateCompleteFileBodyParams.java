package de.muenchen.dms.akte.anlegen.complete;

import io.swagger.v3.oas.annotations.media.Schema;
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
public class CreateCompleteFileBodyParams {

  @Schema(description = "Objekt-ID der Betreffseinheit", example = "COO.1.2301.1.683")
  private String apentry;

  @Schema(description = "Titel", example = "2016 Anträge Firma XY")
  private String shortnamefile;

  @Schema(description = "Titel", example = "2016 Anträge Firma XY")
  private String shortnameprocedure;

  @Schema(description = "Titel", example = "2016 Anträge Firma XY")
  private String shortnamedocument;

  @Schema(
      description =
          """
Objekt-ID (COO-Adresse) der Aktenführenden Organisationseinheit.
Als Organisationseinheit der Sachakte wird die Organisationseinheit des darüber liegenden Aktenplankennzeichens gesetzt.
Sofern keine Organisationseinheit auf dem Aktenplankennzeichen definiert ist, wird die Organisationseinheit der Rolle des Benutzers verwendet.
""",
      example = "COO.1.2300.1.847")
  private String fileouobj;

  public static CreateCompleteFileBodyParams sample() {
    return CreateCompleteFileBodyParams.builder()
        .apentry("COO.1.2301.1.683")
        .shortnamefile("2016 Anträge Firma XY")
        .shortnameprocedure("2016 Anträge Firma XY")
        .shortnamedocument("2016 Anträge Firma XY")
        .fileouobj("COO.1.2300.1.847")
        .build();
  }
}
