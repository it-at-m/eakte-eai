/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.ausgang.anlegen;

import de.muenchen.dms.common.model.DMSContainer;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateOutgoingAnfrageDTO extends DMSContainer {
  @Builder
  public CreateOutgoingAnfrageDTO(
      String referrednumber,
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      String referredincoming,
      OffsetDateTime outgoingdate,
      String subfiletype,
      String doctemplate,
      String incattachments) {
    super(shortname, filesubj, objterms, accdef);
    this.referrednumber = referrednumber;
    this.referredincoming = referredincoming;
    this.outgoingdate = outgoingdate;
    this.subfiletype = subfiletype;
    this.doctemplate = doctemplate;
    this.incattachments = incattachments;
  }

  @Schema(
      requiredMode = RequiredMode.REQUIRED,
      description =
          """
      Objekt-ID (COO-Adresse) des Vorgangs
      Wenn kein Vorgang angegeben wird, soll das erzeugte Ausgangsdokument am Schreibtisch des Benutzers
      abgelegt werden.
      """,
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          """
            Bezug zu Eingang
            Bei Bedarf kann die COO-Adresse des Eingangs angegeben werden,
            damit ein direkter Bezug zwischen Ein- und Ausgang hergestellt werden kann.
            """,
      example = "COO.1.2301.1.1042441")
  private String referredincoming;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description = "Termin (Datum/Uhrzeit)",
      example = "2016-10-26T18:16:29")
  private OffsetDateTime outgoingdate;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          """
            Dokumenttyp
            Plausibilitätsprüfung: Der Name eines im System vorhandenen Dokumenttyps muss angegeben werden.
            Eine dazugehörige Vorlage (doctemplate) muss nicht verwendet werden. Es können auch finalisierte
            Schriftstücke der Fachanwendung über GiAttachmentType übertragen und verwendet werden.
            """,
      example = "Test Dokumenttyp")
  private String subfiletype;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          """
            Vorlage
            Der Name einer im System vorhandenen Vorlage kann angegeben werden.
            Es kann der Name einer Vorlage angegeben werden.
            In dieser Vorlage können beispielsweise Metadaten automatisiert über DocProperty
            ausgegeben werden.
            Die angegebene Vorlage muss im angegebenen Dokumenttyp enthalten sein.
            Alternativ können finalisierte Schriftstücke auch über GiAttachmentType übertragen werden.
            """,
      example = "LHM Schreiben Extern")
  private String doctemplate;

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description = "Sonstige Anlagen",
      example = "Ausfüllhilfe Antrag auf Baugenehmigung")
  private String incattachments;

  public static CreateOutgoingAnfrageDTO sample() {
    return CreateOutgoingAnfrageDTO.builder()
        .referrednumber("COO.1.2301.1.1042432")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .shortname("Anfrage Baugenehmigung")
        .referredincoming("COO.x.xxxx.x.xxxxxxx")
        .outgoingdate(OffsetDateTime.now().withNano(0))
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .subfiletype("Test Dokumenttyp")
        .doctemplate("LHM Schreiben Extern")
        .incattachments("Bebauungsplan")
        .build();
  }
}
