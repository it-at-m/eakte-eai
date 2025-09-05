/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang;

import de.muenchen.dms.common.model.DMSContainer;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public abstract class VorgangBasisDTO extends DMSContainer {
  protected VorgangBasisDTO(
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String procremark) {
    super(shortname, filesubj, objterms, accdef);
    this.fileruntimefrom = fileruntimefrom;
    this.fileruntimetill = fileruntimetill;
    this.procremark = procremark;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Laufzeitbeginn",
      example = "2017-01-13T17:09:42.411+01:00")
  private OffsetDateTime fileruntimefrom;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Laufzeitende",
      example = "2017-01-13T17:09:42.411+01:00")
  private OffsetDateTime fileruntimetill;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Bemerkung für Prozessverantwortliche",
      example = "Warte auf Rückmeldung")
  private String procremark;
}
