/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.aendern;

import de.muenchen.dms.common.model.DMSContainerBase;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UpdateFileDTO extends DMSContainerBase {
  @Builder
  public UpdateFileDTO(
      String shortname,
      String filesubj,
      String objterms,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String filearchivetimeOffltdeadline,
      String filelifetimeOffltdeadline,
      String offltdispstate,
      String procedureaccdef) {
    super(shortname, filesubj, objterms);
    this.fileruntimefrom = fileruntimefrom;
    this.fileruntimetill = fileruntimetill;
    this.filearchivetimeOffltdeadline = filearchivetimeOffltdeadline;
    this.filelifetimeOffltdeadline = filelifetimeOffltdeadline;
    this.offltdispstate = offltdispstate;
    this.procedureaccdef = procedureaccdef;
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
      description =
          """
            Eindeutiger Name des Frist-Komponentenobjekt.
            Bei der Auswahl des jeweiligen Komponentenobjekte muss beachtet werden, dass der Name die im Objekt
            enthaltenen Einstellungen wiederspiegelt. Aktuell sind im München-Standard folgende Werte vorhanden:
                     • 1 Jahr
                     • 1 Monat
                     • 1 Tag
                     • 1 Woche
                     • 10 Jahre
                     • 15 Jahre
                     • 20 Jahre
                     • 3 Jahre
                     • 3 Monate
                     • 30 Jahre
                     • 5 Jahre
                     • 6 Monate
                     • 7 Jahre
""",
      example = "10 Jahre")
  private String filearchivetimeOffltdeadline;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
        Eindeutiger Name des Frist-Komponentenobjekt.
        Bei der Auswahl des jeweiligen Komponentenobjekte muss beachtet werden, dass der Name die im Objekt
        enthaltenen Einstellungen wiederspiegelt. Aktuell sind im München-Standard folgende Werte vorhanden:
        • 1 Jahr
        • 1 Monat
        • 1 Tag
        • 1 Woche
        • 10 Jahre
        • 15 Jahre
        • 20 Jahre
        • 3 Jahre
        • 3 Monate
        • 30 Jahre
        • 5 Jahre
        • 6 Monate
        • 7 Jahre
""",
      example = "10 Jahre")
  private String filelifetimeOffltdeadline;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
            Gewünschte Aussonderungsart,
            • „DISPSTATE_ARCHIVE“ - für „A - Archivwürdig“
            • „DISPSTATE_TRANSFER“ - für „Transfer“
            • „DISPSTATE_DESTROY“ - für „V - Vernichten“
            • „DISPSTATE_TRANSFER_NO_EVIDENCE“ für „Sammlungsgut“
            • „DISPSTATE_DM_B“ für „B - Bewerten“""",
      example = "DISPSTATE_ARCHIVE")
  private String offltdispstate;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
            Zugriffsdefinition für Vorgänge
            Eindeutiger Name der Zugriffsdefinition. z.B.:
            • Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)
            • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit
            • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit und Zentralregistratur
            • Zugriffsdefinition für Schriftgutobjekte des Eigentümers
            Über diese Eigenschaft wird definiert welche Zugriffsdefinition beim Anlegen eines neuen Vorgangs verwendet wird.""",
      example = "Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit")
  private String procedureaccdef;
}
