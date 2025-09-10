package de.muenchen.dms.businessdata.update;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateBusinessDataValueDTO {

  @Builder
  public UpdateBusinessDataValueDTO(String reference, String value) {
    this.reference = reference;
    this.value = value;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Referenz des Fachdatum",
      example = "data_dognumber")
  private String reference;

  @Schema(description = "Wert des Fachdatum", example = "458965")
  private String value;
}
