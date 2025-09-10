package de.muenchen.dms.search.apentry;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchApentryDTO {

  @Builder
  public SearchApentryDTO(String basenr, String subjareaspecreference) {
    this.basenr = basenr;
    this.subjareaspecreference = subjareaspecreference;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Aktenplankennzeichen",
      example = "A1 01 01 01 01")
  private String basenr;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Ableitung",
      example = "Ableitung1")
  private String subjareaspecreference;
}
