/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import de.muenchen.dms.common.model.UserFormsReferenz;
import de.muenchen.dms.vorgang.VorgangBasisDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.List;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateProcedureDTO extends VorgangBasisDTO {
  @Builder
  public CreateProcedureDTO(
      String referrednumber,
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String procremark,
      String filetype,
      String definition,
      List<UserFormsReferenz> userformsdata) {
    super(shortname, filesubj, objterms, accdef, fileruntimefrom, fileruntimetill, procremark);
    this.referrednumber = referrednumber;
    this.filetype = filetype;
    this.definition = definition;
    this.userformsdata = userformsdata;
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
            Einer der drei kann beim Anlegen eines Vorgangs übergeben werden:
            • Elektronisch
            • Papier
            • Hybrid.
            Wird ein falscher Wert angegeben, wird eine Fehlermeldung zurückgegeben:
            Status -1 und "Art des Vorgangs" enthält einen ungültigen Wert.“
            Wenn kein Wert eingetragen wird, wird „Elektronisch“ hinterlegt.
          """,
      example = "Elektronisch")
  private String filetype;

  @Schema() private String definition;

  @Schema private List<UserFormsReferenz> userformsdata;
}
