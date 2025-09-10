package de.muenchen.dms.addressee.get;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAddresseeType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.GetAddresseeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.TestBeispielErzeuger;
import de.muenchen.dms.common.model.AddresseeReferenz;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class GetAddresseeResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;

  private GetAddresseeResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new GetAddresseeResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(GetAddresseeResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    GetAddresseeResponseDTO response =
        mapper.createParser(body).readValueAs(GetAddresseeResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    Assertions.assertEquals(response.getAddressees(), erzeugeBeispielAddresseeReferenz());
  }

  private GetAddresseeResponse erzeugeDmsAntwortOhneFehler() {
    GetAddresseeResponse response = new GetAddresseeResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);

    ArrayOfLHMBAI151700GIAddresseeType listAddresseeType;
    listAddresseeType =
        TestBeispielErzeuger.erzeugeBeispielListAddresseeReferenz(
            TestBeispielErzeuger.erzeugeBeispielAddresseeReferenz(
                null,
                "Papier",
                "Staatskanzlei Bayern",
                "Dr.",
                "Thomas",
                "Mayer",
                "Maximilianstraße",
                "3",
                "80200",
                "München",
                "Deutschland",
                "staatskanzlei@muenchen.de",
                "00498524885"));
    response.setAddressees(listAddresseeType);
    return response;
  }

  private static List<AddresseeReferenz> erzeugeBeispielAddresseeReferenz() {

    return Collections.singletonList(
        AddresseeReferenz.builder()
            .addrdate(null)
            .addrtransmedia("Papier")
            .addrOrgName("Staatskanzlei Bayern")
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
            .build());
  }
}
