package de.muenchen.dms.schriftstueck.eingang.aendern;

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
public class UpdateIncomingAnfrageDTO extends DMSContainer {
  @Builder
  public UpdateIncomingAnfrageDTO(
      String shortname,
      String accdef,
      String foreignnr,
      String filesubj,
      OffsetDateTime delivery,
      String documentremarks,
      String incattachments,
      String objterms) {
    super(shortname, filesubj, objterms, accdef);
    this.foreignnr = foreignnr;
    this.delivery = delivery;
    this.documentremarks = documentremarks;
    this.incattachments = incattachments;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Fremdes Geschäftszeichen",
      example = "A2016-10-2016-Landeshauptstadt-München")
  private String foreignnr;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Eingangsdatum des Objekts",
      example = "2016-10-26")
  private OffsetDateTime delivery;

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

  public static UpdateIncomingAnfrageDTO sample() {
    return UpdateIncomingAnfrageDTO.builder()
        .shortname("Anfrage Baugenehmigung")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .foreignnr("A2016-10-2016-Landeshauptstadt-München")
        .filesubj(
            "Schreiben der Firma Mustermann vom 21.10.2016 bezüglich Beantragung Baugenehmigung.")
        .delivery(OffsetDateTime.now().withNano(0))
        .documentremarks("Antrag auf Baugenehmigung")
        .incattachments("Bebauungsplan")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .build();
  }
}
