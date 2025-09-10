package de.muenchen.dms.search.procedure;

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
public class SearchProcedureRequestDTO {

  @Schema(description = "COO-Adresse der Akte", example = "COO.1.2300.1.847")
  private String referrednumber;

  @Schema(description = "Geschäftszeichen", example = "A1 01 01 01 01/00010/0001")
  private String filereference;

  @Schema(description = "Name", example = "Vorgang 1")
  private String objmlname;

  @Schema(description = "Betreff", example = "Betreff des Vorgangs")
  private String filesubj;
}
