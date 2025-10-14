/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.anlegen;

import de.muenchen.dms.akte.AkteBasisDTO;
import java.time.OffsetDateTime;
import java.util.List;

import de.muenchen.dms.common.model.UserFormsReferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class CreateFileDTO extends AkteBasisDTO {
  @Builder
  public CreateFileDTO(
      String apentry,
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String fileouobj,
      String procedureaccdef,
      String definition,
      List<UserFormsReferenz> userformsdata) {
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
    this.definition = definition;
    this.userformsdata = userformsdata;
  }

  @Schema() private String definition;

  @Schema private List<UserFormsReferenz> userformsdata;
}
