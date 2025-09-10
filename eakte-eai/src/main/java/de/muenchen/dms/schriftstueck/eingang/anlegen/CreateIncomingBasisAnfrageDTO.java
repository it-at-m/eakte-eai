/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.eingang.anlegen;

import de.muenchen.dms.common.model.DMSContainer;
import io.swagger.v3.oas.annotations.media.Schema;
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
public class CreateIncomingBasisAnfrageDTO extends DMSContainer {
  @Builder
  public CreateIncomingBasisAnfrageDTO(
      String referrednumber,
      Boolean useou,
      String shortname,
      String accdef,
      String foreignnr,
      String filesubj,
      String documentremarks,
      String incattachments,
      String objterms,
      OffsetDateTime delivery) {
    super(shortname, filesubj, objterms, accdef);
    this.referrednumber = referrednumber;
    this.useou = useou;
    this.delivery = delivery;
    this.foreignnr = foreignnr;
    this.documentremarks = documentremarks;
    this.incattachments = incattachments;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des Vorgangs",
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
      Der Wert wird nur ausgelesen wenn (referrednumber == NULL) d.h. keine Vorgangszuordnung stattfindet.
      Wenn true, wird die Aktivität „Neuer Eingang“ an die OE (ohne Benutzer vorgeschrieben).
      Wenn kein Wert (oder false) übermittelt wird, wird die direkte Vorschreibung an den Benutzer durchgeführt.
      """,
      example = "false")
  private Boolean useou;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Eingangsdatum des Objekts",
      example = "2016-10-26")
  private OffsetDateTime delivery;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Fremdes Geschäftszeichen",
      example = "A2016-10-2016-Landeshauptstadt-München")
  private String foreignnr;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Dokumentbezogene Hinweise",
      example = "Antrag auf Baugenehmigung")
  private String documentremarks;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Sonstige Anlagen",
      example = "Bebauungsplan")
  private String incattachments;

  public static CreateIncomingBasisAnfrageDTO sample() {
    return CreateIncomingBasisAnfrageDTO.builder()
        .objterms("Firma XY; Anträge; Anträge 2016")
        .shortname("Anfrage Baugenehmigung")
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .referrednumber("COO.1.2301.1.1042432")
        .useou(Boolean.FALSE)
        .delivery(OffsetDateTime.now().withNano(0))
        .foreignnr("A2016-10-2016-Landeshauptstadt-München")
        .documentremarks("Antrag auf Baugenehmigung")
        .incattachments("Bebauungsplan")
        .build();
  }
}
