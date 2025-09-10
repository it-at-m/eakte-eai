/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte;

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
public abstract class AkteBasisDTO extends DMSContainer {

  protected AkteBasisDTO(
      String apentry,
      String shortname,
      String filesubj,
      String objterms,
      String accdef,
      OffsetDateTime fileruntimefrom,
      OffsetDateTime fileruntimetill,
      String fileouobj,
      String procedureaccdef) {
    super(shortname, filesubj, objterms, accdef);
    this.apentry = apentry;
    this.fileruntimefrom = fileruntimefrom;
    this.fileruntimetill = fileruntimetill;
    this.fileouobj = fileouobj;
    this.procedureaccdef = procedureaccdef;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Aktenplankennzeichen Objekt-ID",
      example = "COO.1.2301.1.683")
  private String apentry;

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
          "Objekt-ID (COO-Adresse) der aktenführenden Organisationseinheit.\n"
              + """
                Als Organisationseinheit der Akte wird die Organisationseinheit des darüber liegenden Aktenplankennzeichens gesetzt.
                Sofern keine Organisationseinheit auf dem Aktenplankennzeichen definiert ist, wird die Organisationseinheit der ersten Rolle des Benutzers verwendet.""",
      example = "COO.1.2300.1.847")
  private String fileouobj;

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
