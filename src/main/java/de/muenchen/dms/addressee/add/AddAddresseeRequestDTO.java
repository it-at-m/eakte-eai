package de.muenchen.dms.addressee.add;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AddAddresseeRequestDTO {

  @Builder
  public AddAddresseeRequestDTO(
      OffsetDateTime addrdate,
      String addrtransmedia,
      String addrorgname,
      String addrtitle,
      String addrfirstname,
      String addrname,
      String addrstreet,
      String addrstreetnumber,
      String addrzipcode,
      String addrcity,
      String addrcountry,
      String addremail,
      String addradditional5) {
    this.addrdate = addrdate;
    this.addrtransmedia = addrtransmedia;
    this.addrorgname = addrorgname;
    this.addrtitle = addrtitle;
    this.addrfirstname = addrfirstname;
    this.addrname = addrname;
    this.addrstreet = addrstreet;
    this.addrstreetnumber = addrstreetnumber;
    this.addrzipcode = addrzipcode;
    this.addrcity = addrcity;
    this.addrcountry = addrcountry;
    this.addremail = addremail;
    this.addradditional5 = addradditional5;
  }

  @Schema(description = "Briefdatum des Objektes", example = "2016-02-25T")
  private OffsetDateTime addrdate;

  @Schema(
      description =
          """
    Versandart für den jeweiligen Adressaten
    Folgende Werte sind möglich:
    • Papier
    • E-Fax
    • E-Mail
    • XML-Dokument
    • Intern
    Wenn kein Wert übertragen wird, soll Papier eingetragen werden.
""",
      example = "Papier")
  private String addrtransmedia;

  @Schema(description = "Organisationsname", example = "Staatskanzlei Bayern")
  private String addrorgname;

  @Schema(description = "Titel", example = "Dr.")
  private String addrtitle;

  @Schema(description = "Vorname", example = "Thomas")
  private String addrfirstname;

  @Schema(description = "Nachname", example = "Mayer")
  private String addrname;

  @Schema(description = "Straße", example = "Maximilianstraße")
  private String addrstreet;

  @Schema(description = "Hausnummer", example = "3")
  private String addrstreetnumber;

  @Schema(description = "Postleitzahl", example = "80200")
  private String addrzipcode;

  @Schema(description = "Ort", example = "München")
  private String addrcity;

  @Schema(description = "Land", example = "Deutschland")
  private String addrcountry;

  @Schema(description = "E-Mail-Adresse", example = "staatskanzlei@muenchen.de")
  private String addremail;

  @Schema(description = "E-Fax", example = "00498524885")
  private String addradditional5;
}
