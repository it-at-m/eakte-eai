package de.muenchen.dms.addressee.get;

import de.muenchen.dms.common.model.AddresseeReferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
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
public class GetAddresseeResponseDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "List of Addressees",
      example =
          """
                        <AddreesseeType>
                        <LHMBAI_15_1700_addreesseetype>
                        <LHMBAI_15_1700_addrdate>2016-02-25T</LHMBAI_15_1700_addrdate>
                        <LHMBAI_15_1700_addrtransmedia>Papier</LHMBAI_15_1700_addrtransmedia>"
                        <LHMBAI_15_1700_addrOrgName>Staatskanzlei Bayern</LHMBAI_15_1700_addrOrgName>
                        <LHMBAI_15_1700_addrtitle>Dr.</LHMBAI_15_1700_addrtitle>
                        <LHMBAI_15_1700_addrfirstname>Thomas</LHMBAI_15_1700_addrfirstname>
                        <LHMBAI_15_1700_addrname>Mayer</LHMBAI_15_1700_addrname>
                        <LHMBAI_15_1700_addrstreet>Maximilianstraße</LHMBAI_15_1700_addrstreet>
                        <LHMBAI_15_1700_addrstreetnumber>3</LHMBAI_15_1700_addrstreetnumber>
                        <LHMBAI_15_1700_addrzipcode>80200</LHMBAI_15_1700_addrzipcode>
                        <LHMBAI_15_1700_addrcity>München</LHMBAI_15_1700_addrcity>
                        <LHMBAI_15_1700_addrcountry>Deutschland</LHMBAI_15_1700_addrcountry>
                        <LHMBAI_15_1700_addremail>staatskanzlei@muenchen.de</LHMBAI_15_1700_addremail>
                        <LHMBAI_15_1700_addradditional5>00498524885</LHMBAI_15_1700_addradditional5>
                        </LHMBAI_15_1700_addreesseetype>
                        </AddreesseeType>""")
  private List<AddresseeReferenz> addressees;
}
