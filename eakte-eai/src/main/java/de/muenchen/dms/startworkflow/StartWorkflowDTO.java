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
public class StartWorkflowDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          "COO-Adresse der Muster für Verfügung\n"
              + "Anmerkung: Titel ist nicht eindeutig, da es im System mehrere „Muster für Verfügung“ mit dem gleichen Namen/Titel gibt.",
      example = "COO.1.1001.1.6485")
  private String workflowpattern;
}
