package de.muenchen.dms.schriftstueck.ausgang.lesen;

import de.muenchen.dms.common.model.DMSContainer;
import de.muenchen.dms.common.model.MetadataReferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReadOutgoingAntwortDTO extends DMSContainer {
  @Builder(builderMethodName = "builderReadOutgoing")
  public ReadOutgoingAntwortDTO(
      String objname,
      String referrednumber,
      String shortname,
      String referredincoming,
      OffsetDateTime outgoingdate,
      String filesubj,
      String subfiletype,
      String incattachments,
      String searchalso,
      String objterms,
      List<MetadataReferenz> gimetadatatype,
      String accdef,
      String businessapp) {
    super(shortname, filesubj, objterms, accdef);
    this.objname = objname;
    this.referrednumber = referrednumber;
    this.referredincoming = referredincoming;
    this.outgoingdate = outgoingdate;
    this.subfiletype = subfiletype;
    this.incattachments = incattachments;
    this.gimetadatatype = gimetadatatype;
    this.searchalso = searchalso;
    this.businessapp = businessapp;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Ausgangskennzeichen",
      example =
          "Ausgangsschreiben zum Antrag auf Baugenehmigung Firma Mustermann (0010 A20011-4-0006-0006)")
  private String objname;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des Vorgangs",
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Bezug zu Eingang",
      example = "COO.1.2301.1.1042441")
  private String referredincoming;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Termin (Datum/Uhrzeit)",
      example = "2016-10-26T18:16:29+01:00")
  private OffsetDateTime outgoingdate;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Dokumenttyp",
      example = "Vorlagen")
  private String subfiletype;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Sonstige Anlagen",
      example = "Ausfüllhilfe Antrag auf Baugenehmigung")
  private String incattachments;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Datei",
      example =
          """
              {
                  "objaddress": "COO.1.2301.1.1041875",
                  "filename": "Antrag auf Baugenehmigung",
                  "fileextension": "pdf",
                  "objclass": "PDF-Dokument",
                  "contsize": "243",
                  "objcreatedby": {
                    "string": [
                      "musterfraum"
                    ]
                  },
                  "objcreatedat": "2018-07-23T08:59:52+01:00",
                  "objchangedby": {
                    "string": [
                      "mustermannm"
                    ]
                  },
                  "objmodifiedat": "2018-07-23T09:23:52+01:00"
                }""")
  private List<MetadataReferenz> gimetadatatype;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Keine Funktion",
      example = "Bescheide Firma Mustermann 2015")
  private String searchalso;

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Businessapp",
      example = "businessapp")
  private String businessapp;

  public static ReadOutgoingAntwortDTO sample() {
    return ReadOutgoingAntwortDTO.builderReadOutgoing()
        .objname(
            "Ausgangsschreiben zum Antrag auf Baugenehmigung Firma Mustermann (0010 A20011-4-0006-0006)")
        .referrednumber("COO.1.2301.1.1042432")
        .shortname("Anfrage Baugenehmigung")
        .referredincoming("COO.x.xxxx.x.xxxxxxx")
        .outgoingdate(OffsetDateTime.now().withNano(0))
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung")
        .subfiletype("Test Dokumenttyp")
        .incattachments("Bebauungsplan")
        .searchalso("Bescheide Firma Mustermann 2015")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .gimetadatatype(erzeugeBeispielMetadataReferenz())
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .businessapp("Businessapp")
        .build();
  }

  public static List<MetadataReferenz> erzeugeBeispielMetadataReferenz() {
    XMLGregorianCalendar date = null;
    return Collections.singletonList(
        MetadataReferenz.builder()
            .objaddress("COO.1.2301.1.1041875")
            .filename("FileName A")
            .fileextension("pdf")
            .objclass("PDF-Dokument")
            .contsize("1024")
            .objcreatedby(null)
            .objcreatedat(date)
            .objchangedby(null)
            .objmodifiedat(date)
            .build());
  }
}
