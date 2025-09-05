package de.muenchen.dms.vorgang.anlegen.fromtemplate;

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
public class CreateProcedureFromTemplateRequestDTO {

  @Schema(
      description = "COO-Addresse der Akte",
      example = "COO.1.2300.1.847",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String referrednumber;

  @Schema(
      description = "COO-Addresse der Vorlage",
      example = "COO.1.2300.1.73641",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String template;

  public CreateProcedureFromTemplateRequestDTO sample() {
    return CreateProcedureFromTemplateRequestDTO.builder()
        .referrednumber("COO.1.2300.1.847")
        .template("COO.1.2300.1.73641")
        .build();
  }
}
