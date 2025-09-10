package de.muenchen.dms.startworkflow;

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
public class StartWorkflowResponseDTO {

  @Schema(description = "Objekt-ID (COO-Adresse) des Prozesses", example = "COO.1.2301.1.1042441")
  private String processid;
}
