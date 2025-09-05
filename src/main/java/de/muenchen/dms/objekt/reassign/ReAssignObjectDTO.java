package de.muenchen.dms.objekt.reassign;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReAssignObjectDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "COO-Adresse des neuen darüberliegenden Objektes (Betreffseinheit oder Akte)",
      example = "COO.1.2302.1.76")
  private String newtarget;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "COO-Adresse der Organisationseinheit",
      example = "COO.1.23.10.8854")
  private String newoe;
}
