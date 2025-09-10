package de.muenchen.dms.schriftstueck.anlegen;

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
public class CreateContentObjectAntwortDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des erzeugten Schrifstückes.",
      example = "COO.1.2301.1.1042465")
  private String objid;

  public static CreateContentObjectAntwortDTO sample() {
    return CreateContentObjectAntwortDTO.builder().objid("COO.1").build();
  }
}
