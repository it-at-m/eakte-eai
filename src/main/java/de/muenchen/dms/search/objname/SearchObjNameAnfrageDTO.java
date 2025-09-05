package de.muenchen.dms.search.objname;

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
public class SearchObjNameAnfrageDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Suchstring für Objektname",
      example = "Test*")
  private String searchstring;

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description =
          """
        Referenz der Objektklasse\n
        • Aktenplan COOELAK@1.1001:Fileplan\n
        • Aktenplaneintrag COOELAK@1.1001:SubjectArea\n
        • Ausgang COOELAK@1.1001:Outgoing\n
        • AutoCAD Zeichnung FSCAUTOCAD@1.1001:Drawing\n
        • Benutzer COOSYSTEM@1.1:User\n
        • Bericht FSCAREXT@1.1001:Report\n
        • Bild FSCWEBCONT@1.1001:ImageObject\n
        • Dokumentkategorie FSCFOLIO@1.1001:DocumentCategory\n
        • Dokumenttyp COOELAK@1.1001:Subject\n
        • Dokument  COOELAK@1.1001:SubFileDocument\n
        (bei Angabe dieser Objektklasse, sollten Eingangs-, und Ausgangsdokumente angezeigt werden)\n
        • E-Mail (Microsoft Office Outlook) COOMAPI@1.1:MailObject\n
        • E-Mail (MIME) FSCMIME@1.1001:MIMEObject\n
        • Eingang COOELAK@1.1001:Incoming\n
        • Freemind Mindmap CFGMUENCHEN@15.1700:FreemindMindmap\n
        • Frist ELAKGOV@1.1001:Deadline\n
        • GIF-Objekt FSCWEBCONT@1.1001:GIFObject\n
        • Hewlett Packard Graphic Language CFGMUENCHEN@15.1700:HPGraphicLanguage\n
        • Inhalt (erweitert) CFGMUENCHEN@15.1700:GenericContent\n
        • Inhalt (unbekannter Typ) GENCONT@1.1:ContentObject\n
        • JPEG-Objekt FSCWEBCONT@1.1001:JPEGObject\n
        • Kostenstelle CFGMUENCHEN@15.1700:CostCenter\n
        • Medieninhalt FSCDIGITALASSET@1.1001:DigitalAsset\n
        • Microsoft Excel-Arbeitsblatt COOMSOFFICE@1.1:ExcelObject\n
        • Microsoft PowerPoint-Präsentation COOMSOFFICE@1.1:PowerPointObject\n
        • Microsoft Project-Objekt COOMSPROJECT@1.1001:MSProject\n
        • Microsoft Visio-Zeichnung VISIO@1.1:VisioObject\n
        • Microsoft Word-Objekt COOMSOFFICE@1.1:WinWordObject\n
        • OpenDocument Formel FSCOOFFICE@1.1001:OOorgMathObject\n
        • OpenOffice.org Vorlage CFGMUENCHEN@15.1700:OOorgTemplateObject\n
        • Ordner COODESK@1.1:Folder\n
        • Organisation FSCFOLIO@1.1001:Organisation\n
        • Organisationseinheit COOSYSTEM@1.1:Group\n
        • PDF-Dokument FSCACROBAT@1.1:PDFObject\n
        • Person FSCFOLIO@1.1001:Person\n
        • Personenakte CFGMUENCHEN@15.1700:PersonalSubjectAreaFile\n
        • PNG-Objekt FSCWEBCONT@1.1001:PNGObject\n
        • Postkorb FSCVGOV@1.1001:Inbox\n
        • Präsentation FSCOOFFICE@1.1001:OOorgPresObject\n
        • Projektordner COOELAK@1.1001:ProjectFolder\n
        • Rechnungsposition CFGMUENCHEN@15.1700:InvoiceItem\n
        • Rich Text Format CFGMUENCHEN@15.1700:RichTextFormat\n
        • Sachakte DEPRECONFIG@15.1001:SubjectAreaFile\n
        • Schlagwort FSCTERM@1.1001:Term\n
        • Schmierzettel ELAKGOV@1.1001:FileMemo\n
        • Schriftstück COOSYSTEM@1.1:ContentObject\n
        • Serena OpenProj Projekt CFGMUENCHEN@15.1700:SerenaOpenProj\n
        • Suchordner COOQBOL@1.1:GenericStoredQBOL\n
        • Tabelle FSCOOFFICE@1.1001:OOorgCalcObject\n
        • Teamroom FSCTEAMROOM@1.1001:TeamRoom\n
        • Text-Dokument NOTE@1.1:NoteObject\n
        • Textbaustein COOAR@1.1:BasicTextModuleEx_Web\n
        • Textdokument FSCOOFFICE@1.1001:OOorgTextObject\n
        • TIFF-Objekt FSCWEBCONT@1.1001:TIFFObject\n
        • Verteiler COOELAK@1.1001:DistributionList\n
        • Verteilerliste COOWF@1.1:ParticipantInstance\n
        • Vorgang DEPRECONFIG@15.1001:Procedure\n
        • Vorlagenkategorie COOTC@1.1001:TemplateCategory\n
        • XDOMEA Paket FSCGOVXMLDE@1.1001:XDOMEAPackage\n
        • Zeichnung FSCOOFFICE@1.1001:OOorgDrawObject\n
        • Zip-Archiv WINZIP@1.1001:WinZipObject\n
        (Alle Objekte von Objektklassen, nach denen ein Sachbearbeiter suchen kann.)
        """,
      example = "FSCACROBAT@1.1:PDF Object")
  private String objclass;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Referenz des Fachdatum",
      example = "data_dognumber")
  private String reference;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Wert des Fachdatum",
      example = "458965")
  private String value;

  public static SearchObjNameAnfrageDTO sample() {
    return SearchObjNameAnfrageDTO.builder()
        .searchstring("Test*")
        .objclass("FSCACROBAT@1.1:PDF Object")
        .reference("data_dognumber")
        .value("458965")
        .build();
  }
}
