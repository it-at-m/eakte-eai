package de.muenchen.dms.schriftstueck.lesen;

import de.muenchen.dms.common.model.AttachmentType;
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
public class ReadContentObjectResponseDTO {

  @Schema(
      description = "Objekt-ID (COO-Adresse) des zugehörigen Dokuments",
      example = "COO.1.2301.1.1042465")
  private String referrednumber;

  @Schema() private AttachmentType giattachmenttype;
}
