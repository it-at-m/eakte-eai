package de.muenchen.dms.inbox.createincomingfrominbox;

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
public class CreateIncomingFromInboxRequestDTO {

  @Schema(
      description = "Objekt-ID (COO-Adresse) des Postkorbs",
      example = "COO.1.2301.4.191",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String inboxid;

  @Schema(description = "Objekt-ID (COO-Adresse) des Vorgangs", example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(
      description = "COO-Adressse eines Schriftstücks aus dem Postkorb",
      example = "COO.1.2301.1.1042446",
      requiredMode = Schema.RequiredMode.REQUIRED)
  private String contentid;

  @Schema(description = "Titel", example = "Eingangspost vom 21.10.2016")
  private String shortname;

  @Schema(
      description = "Fremdes Geschäftszeichen",
      example = "A2016-10-2016-Landeshauptstadt-München")
  private String foreignnr;

  @Schema(
      description = "Betreff",
      example = "Schreiben der Firma Mustermann bezüglich Beantragung Baugenehmigung")
  private String filesubj;

  @Schema(description = "Dokumentbezogene Hinweise", example = "Antrag auf Baugenehmigung")
  private String documentremarks;

  @Schema(description = "Sonstige Anlagen", example = "Bebauungsplan")
  private String incattachments;

  @Schema(description = "Schlagworte", example = "Firma XY; Anträge; Anträge 2016")
  private String objterms;
}
