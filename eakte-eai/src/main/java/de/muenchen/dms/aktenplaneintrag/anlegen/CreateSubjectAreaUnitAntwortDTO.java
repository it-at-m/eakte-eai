package de.muenchen.dms.aktenplaneintrag.anlegen;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class CreateSubjectAreaUnitAntwortDTO {
  @Schema(description = "Objekt-ID (COO-Adresse) des Objekts", example = "COO.1.2301.1.1042441")
  private String objid;

  @Schema(
      description = "Objektname des Objekts",
      example = "Eingangspost vom 21.10.2016 (0010 A20 011-4-0006-0001)")
  private String objname;
}
