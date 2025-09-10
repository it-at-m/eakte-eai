package de.muenchen.dms.schriftstueck.aendern;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContentObjectAntwortDTO {

  @Schema(
      description = "Objekt-ID (COO-Adresse) des ersetzten Schriftstücks",
      example = "COO.1.2301.1.1042466")
  private String contentobjid;

  @Schema(
      description = "Objektname des ersetzten Schriftstücks",
      example = "LHM Schreiben Extern vom 11.11.2016 v101")
  private String contentobjname;

  @Schema(
      description =
          "Zugehöriges Dokument, Objekt-ID (COO-Adresse) des Dokuments, in dem das Schriftstück ersetzt worden ist.",
      example = "COO.1.2301.1.1042465")
  private String objid;

  @Schema(
      description = "Objektname des Dokuments, in dem das Schriftstück ersetzt worden ist.",
      example =
          "Ausgangsschreiben zum Antrag auf Baugenehmigung Firma Mustermann (0010 A20 011-4-0006-0006)")
  private String objname;
}
