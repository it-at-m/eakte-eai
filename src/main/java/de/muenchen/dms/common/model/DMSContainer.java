package de.muenchen.dms.common.model;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.media.Schema.RequiredMode;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class DMSContainer extends DMSContainerBase {
  public DMSContainer(String shortname, String filesubj, String objterms, String accdef) {
    super(shortname, filesubj, objterms);
    this.accdef = accdef;
  }

  public DMSContainer(String accdef) {
    this.accdef = accdef;
  }

  @Schema(
      requiredMode = RequiredMode.NOT_REQUIRED,
      description =
          """
            Zugriffsdefinition
            Eindeutiger Name der Zugriffsdefinition. z.B.:
            • Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)
            • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit
            • Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit und Zentralregistratur
            • Zugriffsdefinition für Schriftgutobjekte des Eigentümers
            Hinweis: Hierbei kann die gewünschte Zugriffsdefinition der Akte gesetzt werden. Wenn kein Wert übergeben wird, wird die auf dem Aktenplaneintrag hinterlegte Zugriffsdefinition initialisiert.""",
      example = "Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
  private String accdef;
}
