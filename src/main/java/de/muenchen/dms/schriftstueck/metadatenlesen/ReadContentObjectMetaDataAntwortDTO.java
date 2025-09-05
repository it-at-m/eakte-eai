package de.muenchen.dms.schriftstueck.metadatenlesen;

import de.muenchen.dms.common.model.MetadataReferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import javax.xml.datatype.XMLGregorianCalendar;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ReadContentObjectMetaDataAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Objekt-ID (COO-Adresse) des zugehörigen Dokuments",
      example = "COO.1.2301.1.1042432")
  private String referrednumber;

  @Schema(
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
  private MetadataReferenz gimetadatatype;

  public static ReadContentObjectMetaDataAntwortDTO sample() {
    return ReadContentObjectMetaDataAntwortDTO.builder()
        .referrednumber("COO.1.2301.1.1042432")
        .gimetadatatype(erzeugeBeispielMetadataReferenz())
        .build();
  }

  public static MetadataReferenz erzeugeBeispielMetadataReferenz() {
    XMLGregorianCalendar date = null;
    return MetadataReferenz.builder()
        .objaddress("COO.1.2301.1.1041875")
        .filename("FileName A")
        .fileextension("pdf")
        .objclass("PDF-Dokument")
        .contsize("1024")
        .objcreatedby(null)
        .objcreatedat(date)
        .objchangedby(null)
        .objmodifiedat(date)
        .build();
  }
}
