package de.muenchen.dms.userformsdata.read;

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
public class ReadUserFormsDataResponseDTO {

  @Schema(description = "Ein Wert", example = "COO.1.2300.1.847")
  private String value;
}
