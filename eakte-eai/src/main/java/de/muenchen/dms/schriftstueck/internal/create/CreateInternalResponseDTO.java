package de.muenchen.dms.schriftstueck.internal.create;

import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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
public class CreateInternalResponseDTO {

  @Schema(
      description = "Objekt-ID (COO-Adresse) des erzeugten internen Dokuments.",
      example = "COO.1.2301.1.1042465")
  private String objid;

  @Schema(
      description = "Objektname des erzeugten internen Dokuments",
      example =
          "Internes Dokument zum Antrag auf Baugenehmigung Firma Mustermann (0010 A20 011-4-0006-0006)")
  private String objname;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Liste der Objektadressen und Namen der Schriftstücke im Dokument.",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;
}
