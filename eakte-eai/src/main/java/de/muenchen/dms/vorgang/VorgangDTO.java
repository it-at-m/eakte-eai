/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
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
public class VorgangDTO extends VorgangBasisDTO {

  @Builder
  public VorgangDTO(
      String referrednumber,
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String procremark,
      String filetype,
      String objname,
      String objdocstate,
      String bostate,
      String objowngroup) {
    super(shortname, filesubj, objterms, accdef, fileruntimefrom, fileruntimetill, procremark);
    this.referrednumber = referrednumber;
    this.filetype = filetype;
    this.objname = objname;
    this.objdocstate = objdocstate;
    this.bostate = bostate;
    this.objowngroup = objowngroup;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Objekt-ID (COO-Adresse) der Sachakte",
      example = "COO.1.2301.1.1042406")
  private String referrednumber;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
                    Art des Vorgangs
                    Einer der drei fest definierten Werte:
                    • Elektronisch
                    • Papier
                    • Hybrid.
                  """,
      example = "Elektronisch")
  private String filetype;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Vorgangskennzeichen",
      example = "Anfrage Baugenehmigung (0010 A20 011-4-0006)")
  private String objname;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Status",
      example = "In Bearbeitung")
  private String objdocstate;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Bearbeitungsstatus",
      example = "Erstellt")
  private String bostate;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objektname der zuständigen OE",
      example = "SG A1 0001 (Sachgebiet A1 0001)")
  private String objowngroup;

  @Transient private static OffsetDateTime fileruntimefrom = OffsetDateTime.now();

  /**
   * Beispielvertragsdaten.
   *
   * @return Vertragsdaten
   */
  public static VorgangDTO sample() {
    return VorgangDTO.builder()
        .referrednumber("COO.1.2301.1.1042406")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .shortname("Anfrage Baugenehmigung")
        .objname("Anfrage Baugenehmigung (0010 A20 011-4-0006)")
        .objdocstate("In Bearbeitung")
        .bostate("Erstellt")
        .objowngroup("SG A1 0001 (Sachgebiet A1 0001)")
        .fileruntimefrom(fileruntimefrom)
        .fileruntimetill(fileruntimefrom.plusMonths(5))
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung")
        .procremark("Warte auf Rückmeldung")
        .filetype("Elektronisch")
        .accdef("Zugriffsdefinition für Vorgangsdaten der Organisationseinheit.")
        .build();
  }
}
