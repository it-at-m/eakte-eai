package de.muenchen.dms.addressee.add;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

public class AddAddresseeDTOBeispiel {
  public static AddAddresseeRequestDTO erzeugeAddAddressee() {

    LocalDateTime date = LocalDateTime.of(2024, 1, 1, 12, 0, 0);
    ZoneOffset dateOffset = ZoneOffset.systemDefault().getRules().getOffset(date);
    return AddAddresseeRequestDTO.builder()
        .addrdate(OffsetDateTime.of(date, dateOffset))
        .addrtransmedia("Papier")
        .addrorgname("Staatskanzlei Bayern")
        .addrtitle("Dr.")
        .addrfirstname("Thomas")
        .addrname("Mayer")
        .addrstreet("Maximilianstraße")
        .addrstreetnumber("3")
        .addrzipcode("80200")
        .addrcity("München")
        .addrcountry("Deutschland")
        .addremail("staatskanzlei@muenchen.de")
        .addradditional5("00498524885")
        .build();
  }
}
