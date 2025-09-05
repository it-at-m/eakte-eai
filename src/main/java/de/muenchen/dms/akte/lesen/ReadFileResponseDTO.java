/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.lesen;

import de.muenchen.dms.akte.AkteDTO;
import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/** Diese Klasse enthält die Werte einer erfolgreichen Anfrage nach Aktendaten. */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class ReadFileResponseDTO extends AkteDTO {
  @Builder(builderMethodName = "builderReadFile")
  public ReadFileResponseDTO(
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String fileouobj,
      String procedureaccdef,
      String objname,
      String apentry,
      List<Objektreferenz> giobjecttype) {
    super(
        shortname,
        filesubj,
        objterms,
        accdef,
        fileruntimefrom,
        fileruntimetill,
        fileouobj,
        procedureaccdef,
        objname,
        apentry);
    this.giobjecttype = giobjecttype;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Vorgänge\n" + "Liste der Vorgänge der Akte",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static ReadFileResponseDTO sample() {

    AkteDTO sampleSuper = AkteDTO.sample();

    return ReadFileResponseDTO.builderReadFile()
        .objterms(sampleSuper.getObjterms())
        .shortname(sampleSuper.getShortname())
        .objname(sampleSuper.getObjname())
        .fileruntimefrom(sampleSuper.getFileruntimefrom())
        .fileruntimetill(sampleSuper.getFileruntimetill())
        .filesubj(sampleSuper.getFilesubj())
        .fileouobj(sampleSuper.getFileouobj())
        .procedureaccdef(sampleSuper.getProcedureaccdef())
        .accdef(sampleSuper.getAccdef())
        .apentry(sampleSuper.getApentry())
        .giobjecttype(erzeugeBeispielVorgangsreferenzen())
        .build();
  }

  private static List<Objektreferenz> erzeugeBeispielVorgangsreferenzen() {
    return Arrays.asList(
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207768")
            .objname("Akte 1 - Vorgang 1")
            .build(),
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207769")
            .objname("Akte 1 - Vorgang 2")
            .build());
  }
}
