package de.muenchen.dms.vorgang.anlegen.complete.businessdata;

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
public class CreateCompleteProcedureBusinessDataBodyParams {

  @Schema(
      description = "COO-Adresse des Fachdatum",
      example = "COO.1.2301.1.683",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String businessterm;

  @Schema(
      description = "Wert des Fachdatum",
      example = "78541235",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String businessvalue;

  @Schema(description = "Vorgangstitel", example = "2016 Anträge Firma XY")
  private String shortnameprocedure;

  @Schema(description = "Dokumenttitel", example = "2016 Anträge Firma XY")
  private String shortnamedocument;
}
