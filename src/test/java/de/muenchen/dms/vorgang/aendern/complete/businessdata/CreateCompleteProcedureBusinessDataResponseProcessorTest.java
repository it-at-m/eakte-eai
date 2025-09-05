package de.muenchen.dms.vorgang.aendern.complete.businessdata;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureBusinessDataGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.vorgang.anlegen.complete.businessdata.CreateCompleteProcedureBusinessDataResponseDTO;
import de.muenchen.dms.vorgang.anlegen.complete.businessdata.CreateCompleteProcedureBusinessDataResponseProcessor;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CreateCompleteProcedureBusinessDataResponseProcessorTest {
  private static final String OBJIDPROCEDURE = "COO.1234.1234.1.1234";
  private static final String OBJNAMEPROCEDURE = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDDOCUMENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMEDOCUMENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDCONTENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMECONTENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final int NO_ERROR_CODE = 0;

  private CreateCompleteProcedureBusinessDataResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateCompleteProcedureBusinessDataResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateCompleteProcedureBusinessDataGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private CreateCompleteProcedureBusinessDataGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateCompleteProcedureBusinessDataGIResponse response =
        new CreateCompleteProcedureBusinessDataGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjidprocedure(OBJIDPROCEDURE);
    response.setObjnameprocedure(OBJNAMEPROCEDURE);
    response.setObjiddocument(OBJIDDOCUMENT);
    response.setObjnamedocument(OBJNAMEDOCUMENT);
    response.setObjidcontent(OBJIDCONTENT);
    response.setObjnamecontent(OBJNAMECONTENT);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateCompleteProcedureBusinessDataResponseDTO responseDTO =
        mapper.createParser(body).readValueAs(CreateCompleteProcedureBusinessDataResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(responseDTO.getObjidprocedure(), equalTo(OBJIDPROCEDURE));
    assertThat(responseDTO.getObjnameprocedure(), equalTo(OBJNAMEPROCEDURE));
    assertThat(responseDTO.getObjiddocument(), equalTo(OBJIDDOCUMENT));
    assertThat(responseDTO.getObjnamedocument(), equalTo(OBJNAMEDOCUMENT));
    assertThat(responseDTO.getObjidcontent(), equalTo(OBJIDCONTENT));
    assertThat(responseDTO.getObjnamecontent(), equalTo(OBJNAMECONTENT));
  }
}
