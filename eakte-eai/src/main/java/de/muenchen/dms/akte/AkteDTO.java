/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Transient;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class AkteDTO extends AkteBasisDTO {
  @Builder
  public AkteDTO(
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String fileouobj,
      String procedureaccdef,
      String objname,
      String apentry) {
    super(
        apentry,
        shortname,
        filesubj,
        objterms,
        accdef,
        fileruntimefrom,
        fileruntimetill,
        fileouobj,
        procedureaccdef);
    this.objname = objname;
  }

  @Schema(description = "Aktenzeichen", example = "0010 A20 011-2016 Anträge Firma XY-4")
  private String objname;

  @Transient private static final OffsetDateTime fileruntimefrom = OffsetDateTime.now();

  public static AkteDTO sample() {
    return AkteDTO.builder()
        .objterms("Firma XY; Anträge; Anträge 2016")
        .shortname("2016 Anträge Firma XY")
        .objname("0010 A20 011-2016 Anträge Firma XY-4")
        .fileruntimefrom(fileruntimefrom.minusDays(5))
        .fileruntimetill(fileruntimefrom)
        .filesubj("Alle Anträge der Firma XY aus dem Jahr 2016 werden in dieser Akte abgelegt.")
        .fileouobj("COO.1.2300.1.267")
        .procedureaccdef("Zugriffsdefinition für Vorgangsdaten der Organisationseinheit.")
        .accdef("Zugriffsdefinition für Vorgangsdaten (allgemein lesbar)")
        .apentry("COO.1.2301.1.683")
        .build();
  }
}
