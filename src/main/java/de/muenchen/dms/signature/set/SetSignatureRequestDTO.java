package de.muenchen.dms.signature.set;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SetSignatureRequestDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "COO-Adresse der Unterschriftenart\n"
              + "Anmerkung: Titel ist nicht eindeutig, da es im System mehrere Unterschriftenarten mit dem gleichen Namen/Titel gibt.",
      example = "COO.1.1001.1.6485")
  private String signaturetype;

  @Schema(description = "Bemerkung zur Unterschrift", example = "String")
  private String remark;
}
