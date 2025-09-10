package de.muenchen.dms.aktenplaneintrag.anlegen;

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
public class CreateSubjectAreaUnitAnfrageDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Objekt-ID (COO-Adresse) des übergeordneten Aktenplaneintrags",
      example = "COO.1.2302.1.8")
  private String objaddress;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Aktenplankennzeichen der Betreffseinheit",
      example = "0001 B10 000")
  private String basenr;

  @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED, description = "Ableitung", example = "1")
  private String subjareaspecreference;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Kurzbezeichnung",
      example = "Betreffseinheit B")
  private String shortterm;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Transferfrist",
      example = "10 Jahre")
  private String subjarchiveschedule;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Aufbewahrungsfrist",
      example = "10 Jahre")
  private String subjschedule;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Aussonderungsart",
      example = "A – Archivwürdig")
  private String subjdispstate;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Zugriffsdefinition für untergeordnete Akten",
      example = "allgemein bearbeitbar")
  private String fileaccessdefinition;

  public static CreateSubjectAreaUnitAnfrageDTO sample() {
    return CreateSubjectAreaUnitAnfrageDTO.builder()
        .objaddress("COO.1.2302.1.8")
        .basenr("0001 B10 000")
        .subjareaspecreference("1")
        .shortterm("Betreffseinheit B")
        .subjarchiveschedule("10 Jahre")
        .subjschedule("10 Jahre")
        .subjdispstate("A – Archivwürdig")
        .fileaccessdefinition("allgemein bearbeitbar")
        .build();
  }
}
