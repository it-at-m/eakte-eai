/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.lesen;

import de.muenchen.dms.common.model.Objektreferenz;
import de.muenchen.dms.vorgang.VorgangDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
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
public class ReadProcedureResponseDTO extends VorgangDTO {
  @Builder(builderMethodName = "builderVorgangLesen")
  public ReadProcedureResponseDTO(
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
      String objowngroup,
      List<Objektreferenz> giobjecttype) {
    super(
        referrednumber,
        shortname,
        filesubj,
        objterms,
        accdef,
        fileruntimefrom,
        fileruntimetill,
        procremark,
        filetype,
        objname,
        objdocstate,
        bostate,
        objowngroup);
    this.giobjecttype = giobjecttype;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Auflistung aller Objekte, auf die der Suchstring zutrifft.",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static ReadProcedureResponseDTO sample() {

    VorgangDTO sampleSuper = VorgangDTO.sample();

    return ReadProcedureResponseDTO.builderVorgangLesen()
        .referrednumber(sampleSuper.getReferrednumber())
        .objterms(sampleSuper.getObjterms())
        .shortname(sampleSuper.getShortname())
        .objname(sampleSuper.getObjname())
        .fileruntimefrom(sampleSuper.getFileruntimefrom())
        .fileruntimetill(sampleSuper.getFileruntimetill())
        .filesubj(sampleSuper.getFilesubj())
        .accdef(sampleSuper.getAccdef())
        .objdocstate(sampleSuper.getObjdocstate())
        .objowngroup(sampleSuper.getObjowngroup())
        .procremark(sampleSuper.getProcremark())
        .filetype(sampleSuper.getFiletype())
        .bostate(sampleSuper.getBostate())
        .giobjecttype(erzeugeBeispielDokumentreferenzen())
        .build();
  }

  private static List<Objektreferenz> erzeugeBeispielDokumentreferenzen() {
    return Arrays.asList(
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207968")
            .objname("Akte 1 - Vorgang 1 - Dokument 1")
            .build(),
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207969")
            .objname("Akte 1 - Vorgang 1 - Dokument 2")
            .build());
  }
}
