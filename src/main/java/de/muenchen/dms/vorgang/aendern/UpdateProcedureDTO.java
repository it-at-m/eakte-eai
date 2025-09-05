/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.aendern;

import de.muenchen.dms.vorgang.VorgangBasisDTO;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class UpdateProcedureDTO extends VorgangBasisDTO {
  @Builder
  public UpdateProcedureDTO(
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String procremark,
      String filetype,
      String filearchivetimeOffltdeadline,
      String filelifetimeOffltdeadline,
      String offltdispstate) {
    super(shortname, filesubj, objterms, accdef, fileruntimefrom, fileruntimetill, procremark);
    this.filetype = filetype;
    this.filearchivetimeOffltdeadline = filearchivetimeOffltdeadline;
    this.filelifetimeOffltdeadline = filelifetimeOffltdeadline;
    this.offltdispstate = offltdispstate;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
                    Art des Vorgangs
                    Einer der drei kann beim Anlegen eines Vorgangs übergeben werden:
                    • Elektronisch
                    • Papier
                    • Hybrid.
                    Sofern hier ein falscher Wert oder kein Wert angegeben wird,
                    ändert sich der bestehende Wert des Vorgangs nicht.
                    Dies hat keine Auswirkung auf die weiteren zu ändernden Metadaten.
                  """,
      example = "Elektronisch")
  private String filetype;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Archivierung",
      example = "10 Jahre")
  private String filearchivetimeOffltdeadline;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Lebenszeit der Akte",
      example = "10 Jahre")
  private String filelifetimeOffltdeadline;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
            Gewünschte Aussonderungsart,• „DISPSTATE_ARCHIVE“ - für „ A - Archivwürdig“
            • „DISPSTATE_TRANSFER“ - für„Transfer“
            • „DISPSTATE_DESTROY“ - für „ V - Vernichten“
            • „DISPSTATE_TRANSFER_NO_EVIDENCE“ für „ Sammlungsgut“
            • „DISPSTATE_DM_B“ für „ B - Bewerten“""",
      example = "DISPSTATE_ARCHIVE")
  private String offltdispstate;
}
