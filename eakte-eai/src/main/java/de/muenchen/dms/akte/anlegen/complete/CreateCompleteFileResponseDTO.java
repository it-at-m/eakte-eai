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
public class CreateCompleteFileResponseDTO {

  @Schema(
      description = "Objekt-ID der erzeugten/gefundenen Sachakte",
      example = "COO.1.2301.1.1042406")
  private String objidfile;

  @Schema(
      description = "Objektname der erzeugten/gefundenen Sachakte",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnamefile;

  @Schema(
      description = "Objekt-ID des erzeugten/gefundenen Vorgangs",
      example = "COO.1.2301.1.1042406")
  private String objidprocedure;

  @Schema(
      description = "Objektname des erzeugten/gefundenen Vorgangs",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnameprocedure;

  @Schema(
      description = "Objekt-ID des erzeugten/gefundenen Dokuments",
      example = "COO.1.2301.1.1042406")
  private String objiddocument;

  @Schema(
      description = "Objektname des erzeugten/gefundenen Dokuments",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnamedocument;

  @Schema(
      description = "Objekt-ID des erzeugten/gefundenen Schriftstücks",
      example = "COO.1.2301.1.1042406")
  private String objidcontent;

  @Schema(
      description = "Objektname des erzeugten/gefundenen Schriftstücks",
      example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objnamecontent;
}
