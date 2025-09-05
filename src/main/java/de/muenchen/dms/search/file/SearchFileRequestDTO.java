package de.muenchen.dms.search.file;

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
public class SearchFileRequestDTO {

  @Schema(description = "COO-Adresse des Aktenplankennzeichen", example = "COO.1.2300.1.847")
  private String apentry;

  @Schema(description = "Geschäftszeichen", example = "A1 01 01 01 01/00010")
  private String filereference;

  @Schema(description = "Name", example = "Akte1")
  private String objmlname;

  @Schema(description = "Betreff", example = "Betreff der Akte")
  private String filesubj;
}
