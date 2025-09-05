package de.muenchen.dms.schriftstueck.internal.create;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@EqualsAndHashCode
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateInternalBodyParams {

  @Schema(
      description =
          """
    Objekt-ID (COO-Adresse) des Vorgangs
    Zugehöriger Vorgang (COOELAK@1.1001:referrednumber)
    Wenn kein Vorgang angegeben wird, soll das erzeugte interne Dokument am Schreibtisch des Benutzers abgelegt werden.
""",
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(description = "Titel", example = "Ausgangsschreiben zum Antrag auf Baugenehmigung Firma")
  private String shortname;

  @Schema(
      description =
          """
        Zugriffsdefinition \n
        Eindeutiger Name der Zugriffsdefinition. z.B.: \n
• Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar) \n
• Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit \n
• Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit und Zentralregistratur \n
• Zugriffsdefinition für Schriftgutobjekte des Eigentümers""",
      example = "Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
  private String accdef;

  @Schema(description = "Datum", example = "2016-10-26T18:16:29")
  private OffsetDateTime deliverydate;

  @Schema(
      description =
          """
        Betreff
        Mehrzeilige Felder werden mit Zeilenumbrüchen oder HTML Kodierung &#10; in Abfragen unterstützt.""",
      example = "Rückfrage zum Antrag auf Baugenehmigung bzgl. Bebauungsplan")
  private String filesubj;

  @Schema(
      description =
          """
            Dokumenttyp
            Plausibilitätsprüfung: Der Name eines im System vorhandenen Dokumenttyps muss angegeben werden.
            Eine dazugehörige Vorlage (doctemplate) muss nicht verwendet werden.
            Es können auch finalisierte Schriftstücke der Fachanwendung über GiAttachmentType übertragen und verwendet werden.""",
      example = "Vorlagen")
  private String subfiletype;

  @Schema(
      description =
          """
            Vorlage
            Der Name einer im System vorhandenen Vorlage kann angegeben werden.
            Es kann der Name einer Vorlage angegeben werden. In dieser Vorlage können beispielsweise Metadaten automatisiert über DocProperty ausgegeben werden.
            Die angegebene Vorlage muss im angegebenen Dokumenttyp enthalten sein.
            Alternativ können finalisierte Schriftstücke auch über GiAttachmentType übertragen werden.""",
      example = "LHM Schreiben Extern")
  private String doctemplate;

  @Schema(description = "Sonstige Anlagen", example = "Ausfüllhile Antrag auf Baugenehmigung")
  private String incattachments;

  @Schema(description = "Schlagworte", example = "Firma XY; Anträge; Anträge 2016")
  private String objterms;

  public static CreateInternalBodyParams sample() {
    return CreateInternalBodyParams.builder()
        .referrednumber("COO.1.2301.1.1042432")
        .shortname("Ausgangsschreiben zum Antrag auf Baugenehmigung Firma")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .deliverydate(OffsetDateTime.now().withNano(0))
        .filesubj("Rückfrage zum Antrag auf Baugenehmigung bzgl. Bebauungsplan")
        .subfiletype("Vorlagen")
        .doctemplate("LHM Schreiben Extern")
        .incattachments("Ausfüllhile Antrag auf Baugenehmigung")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .build();
  }
}
