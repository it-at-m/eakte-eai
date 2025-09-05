package de.muenchen.dms.vorgang.anlegen.fromtemplate;

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
public class CreateProcedureFromTemplateResponseDTO {

  @Schema(description = "Objekt-ID des erzeugten Vorgangs", example = "COO.1.2301.1.1042432")
  private String objid;

  @Schema(description = "Name des erzeugten Vorgangs", example = "2016 Anträge Firma XY")
  private String objname;
}
