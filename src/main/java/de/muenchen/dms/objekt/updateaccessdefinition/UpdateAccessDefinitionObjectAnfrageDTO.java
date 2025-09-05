package de.muenchen.dms.objekt.updateaccessdefinition;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateAccessDefinitionObjectAnfrageDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Objektname Einer Zugriffsdefinition",
      example = "Zugriffsdefinition für Schriftgutobjekte des Eigentümers")
  private String objaccdef;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
      Rechtevererbung

      Mögliche Werte: null,0,1,2

      0 oder leer (null): Die Rechtevererbung aus dem Standardprodukt wird verwendet.
      Der neue Wert wird rekursiv gesetzt in Abhängigkeit von den jeweiligen aktuell gesetzten
      Werten der untergeordneten Objekte.

      1: Änderung der Zugriffsdefinition auf dem Objekt und rekursive Vererbung auf alle untergeordneten Objekte
      unabhängig von den jeweiligen aktuell gesetzten Werten der untergeordneten Objekte.

      2: Änderung der Zugriffsdefinition nur auf dem Objekt

      Hinweis: Beim übergeben des Werts 1 auf einer Sachakte vererbt sich die Zugriffsdefinition auf alle in der
      Akte enthaltenen Vorgänge und wieder auf alle Dokumente, die in den Vorgängen enthalten sind.
      Bei einer großen Anzahl von untergeordneten Objekten kann es zu Abbrüchen aufgrund Objektsperren
      oder durch Timeouts kommen.
      """,
      example = "1")
  private Integer authinheritance;
}
