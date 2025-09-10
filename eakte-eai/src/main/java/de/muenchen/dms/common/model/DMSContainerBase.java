package de.muenchen.dms.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DMSContainerBase {
  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description = "Titel",
      example = "2016 Anträge Firma XY")
  private String shortname;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          """
          Betreff (mehrzeilig)
          Mehrzeilige Felder werden mit Zeilenumbrüchen oder HTML Kodierung &#10; in Abfragen unterstützt.
""",
      example = "Alle Anträge der Firma XY aus dem Jahr 2016")
  private String filesubj;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          "Schlagworte, Angabe der Schlagworte getrennt mit „;“. Kein Semikola am Ende angeben.",
      example = "Firma XY; Anträge; Anträge 2016")
  private String objterms;
}
