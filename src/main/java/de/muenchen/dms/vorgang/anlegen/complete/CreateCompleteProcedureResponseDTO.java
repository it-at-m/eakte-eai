package de.muenchen.dms.vorgang.anlegen.complete;

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
public class CreateCompleteProcedureResponseDTO {
  @Schema(
      description = "Objekt-ID (COO-Adresse) des erzeugten/gefundenen Vorgangs",
      example = "COO.1.2301.1.1042406")
  private String objidprocedure;

  @Schema(
      description = "Objektname des erzeugten/gefundenen Vorgangs",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnameprocedure;

  @Schema(
      description = "Objekt-ID (COO-Adresse) des erzeugten/gefundenen Dokuments",
      example = "COO.1.2301.1.1042406")
  private String objiddocument;

  @Schema(
      description = "Objektname des erzeugten/gefundenen",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnamedocument;

  @Schema(
      description = "Objekt-ID (COO-Adresse) des erzeugten/gefundenen Schrifstücks",
      example = "COO.1.2301.1.1042406")
  private String objidcontent;

  @Schema(
      description = "Objektname des erzeugten/gefundenen Schrifstücks",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnamecontent;

  public CreateCompleteProcedureResponseDTO sample() {
    return CreateCompleteProcedureResponseDTO.builder()
        .objidprocedure("COO.1.2301.1.1042406")
        .objnameprocedure("0010 A20 011-2016 Anträge Firma XY-4")
        .objiddocument("COO.1.2301.1.1042406")
        .objnamedocument("0010 A20 011-2016 Anträge Firma XY-4")
        .objidcontent("COO.1.2301.1.1042406")
        .objnamecontent("0010 A20 011-2016 Anträge Firma XY-4")
        .build();
  }
}
