package de.muenchen.dms.vorgang.anlegen.complete;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateCompleteProcedureBodyParams {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Objekt-ID (COO-Adresse) der Sachakte",
      example = "COO.1.2301.1.683")
  private String file;

  @Schema(description = "Titel", example = "2016 Anträge Firma XY")
  private String shortnameprocedure;

  @Schema(description = "Titel", example = "2016 Anträge Firma XY")
  private String shortnamedocument;

  public static CreateCompleteProcedureBodyParams sample() {
    return CreateCompleteProcedureBodyParams.builder()
        .file("COO.1.2301.1.683")
        .shortnameprocedure("2016 Anträge Firma XY")
        .shortnamedocument("2016 Anträge Firma XY")
        .build();
  }
}
