package de.muenchen.dms.objekt.updateaccessdefinition;

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
public class UpdateAccessDefinitionObjectAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des Objekts",
      example = "COO.1.2301.1.1042441")
  private String objid;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objektname des Objekts",
      example = "Eingangspost vom 21.10.2016")
  private String objname;

  public static UpdateAccessDefinitionObjectAntwortDTO sample() {
    return UpdateAccessDefinitionObjectAntwortDTO.builder()
        .objid("COO.1.2301.1.1042441")
        .objname("Eingangspost vom 21.10.2016")
        .build();
  }
}
