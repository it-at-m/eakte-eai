package de.muenchen.dms.schriftstueck.ausgang.update;

import de.muenchen.dms.common.model.DMSContainerBase;
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
public class UpdateOutgoingRequestDTO extends DMSContainerBase {
  @Builder
  public UpdateOutgoingRequestDTO(
      String shortname,
      String accdef,
      String referredincoming,
      OffsetDateTime outgoingdate,
      String filesubj,
      String incattachments,
      String objterms) {
    super(shortname, filesubj, objterms);
    this.accdef = accdef;
    this.referredincoming = referredincoming;
    this.outgoingdate = outgoingdate;
    this.incattachments = incattachments;
  }

  @Schema(
      description =
          """
        Zugriffsdefinition
        Eindeutiger Name der Zugriffsdefinition. z.B.:
        • Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)
        • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit
        • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit und Zentralregistratur
        • Zugriffsdefinition für Schriftgutobjekte des Eigentümers
""",
      example = "Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
  private String accdef;

  @Schema(
      description =
          """
      Bezug zu Eingang
      Bei Bedarf kann die COO-Adresse des Eingangs geändert werden,
      damit ein direkter Bezug zwischen Ein- und Ausgang hergestellt werden kann.
""",
      example = "COO.1.2301.1.1042441")
  private String referredincoming;

  @Schema(description = "Termin (Datum/Uhrzeit)", example = "2016-10-26T18:48:01+01:00")
  private OffsetDateTime outgoingdate;

  @Schema(description = "sonstige Anlagen", example = "Ausfüllhilfe Antrag auf Baugenehmigung")
  private String incattachments;

  public static UpdateOutgoingRequestDTO sample() {
    return UpdateOutgoingRequestDTO.builder()
        .shortname("Anfrage Baugenehmigung")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .referredincoming("COO.x.xxxx.x.xxxxxxx")
        .outgoingdate(OffsetDateTime.now().withNano(0))
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung")
        .incattachments("Bebauungsplan")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .build();
  }
}
