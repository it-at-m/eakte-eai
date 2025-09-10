package de.muenchen.dms.schriftstueck.anlegen;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateContentObjectAnfrageDTO {
  @Builder(builderMethodName = "builderCreateContentObject")
  public CreateContentObjectAnfrageDTO(String referrednumber) {
    this.referrednumber = referrednumber;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des zugehörigen Dokuments",
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema() private List<String> filesubj;

  public static CreateContentObjectAnfrageDTO sample() {
    return CreateContentObjectAnfrageDTO.builderCreateContentObject()
        .referrednumber("COO.1.2301.1.1042432")
        .build();
  }
}
