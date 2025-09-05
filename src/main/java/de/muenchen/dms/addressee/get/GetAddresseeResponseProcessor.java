package de.muenchen.dms.addressee.get;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAddresseeType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.GetAddresseeResponse;
import de.muenchen.dms.common.model.AddresseeReferenz;
import de.muenchen.dms.common.processor.AbstractResponseProcessor;
import java.util.ArrayList;
import java.util.List;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class GetAddresseeResponseProcessor extends AbstractResponseProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final GetAddresseeResponse response = exchange.getIn().getBody(GetAddresseeResponse.class);

    final GetAddresseeResponseDTO dto = wandleGetAddresseeToDTO(response);

    checkResponse(exchange, response.getStatus(), response.getErrormessage(), dto);
  }

  private static GetAddresseeResponseDTO wandleGetAddresseeToDTO(GetAddresseeResponse response) {
    return GetAddresseeResponseDTO.builder()
        .addressees(wandleAddresseeTypeToAddresseeReferenz(response.getAddressees()))
        .build();
  }

  private static List<AddresseeReferenz> wandleAddresseeTypeToAddresseeReferenz(
      ArrayOfLHMBAI151700GIAddresseeType addresseeType) {
    List<AddresseeReferenz> addresseeReferenzen = new ArrayList<>();

    if (addresseeType != null && addresseeType.getLHMBAI151700GIAddresseeType() != null) {
      for (var objectType : addresseeType.getLHMBAI151700GIAddresseeType()) {
        addresseeReferenzen.add(
            AddresseeReferenz.builder()
                .addrdate(objectType.getLHMBAI151700Addrdate())
                .addrtransmedia(objectType.getLHMBAI151700Addrtransmedia())
                .addrOrgName(objectType.getLHMBAI151700Addrorgname())
                .addrtitle(objectType.getLHMBAI151700Addrtitle())
                .addrfirstname(objectType.getLHMBAI151700Addrfirstname())
                .addrname(objectType.getLHMBAI151700Addrname())
                .addrstreet(objectType.getLHMBAI151700Addrstreet())
                .addrstreetnumber(objectType.getLHMBAI151700Addrstreetnumber())
                .addrzipcode(objectType.getLHMBAI151700Addrzipcode())
                .addrcity(objectType.getLHMBAI151700Addrcity())
                .addrcountry(objectType.getLHMBAI151700Addrcountry())
                .addremail(objectType.getLHMBAI151700Addremail())
                .addradditional5(objectType.getLHMBAI151700Addradditional5())
                .build());
      }
    }
    return addresseeReferenzen;
  }
}
