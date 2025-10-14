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
public class ReadUserFormsDataRequestDTO {

  @Schema(
      description = """
            Lese die Userdaten.
            """,
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String reference;
}
