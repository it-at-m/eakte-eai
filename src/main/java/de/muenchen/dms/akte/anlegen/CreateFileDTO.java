/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.anlegen;

import de.muenchen.dms.akte.AkteBasisDTO;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

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
      String procedureaccdef) {
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
  }
}
