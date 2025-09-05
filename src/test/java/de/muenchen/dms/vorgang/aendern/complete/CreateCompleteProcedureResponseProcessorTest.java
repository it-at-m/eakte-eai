package de.muenchen.dms.vorgang.aendern.complete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteProcedureGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.vorgang.anlegen.complete.CreateCompleteProcedureResponseDTO;
import de.muenchen.dms.vorgang.anlegen.complete.CreateCompleteProcedureResponseProcessor;
import java.io.IOException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CreateCompleteProcedureResponseProcessorTest {
  private static final String OBJIDPROCEDURE = "COO.1234.1234.1.1234";
  private static final String OBJNAMEPROCEDURE = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDDOCUMENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMEDOCUMENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDCONTENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMECONTENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final int NO_ERROR_CODE = 0;

  private CreateCompleteProcedureResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new CreateCompleteProcedureResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verabeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateCompleteProcedureGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateCompleteProcedureResponseDTO antwort =
        mapper.createParser(body).readValueAs(CreateCompleteProcedureResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getObjidprocedure(), equalTo(OBJIDPROCEDURE));
    assertThat(antwort.getObjnameprocedure(), equalTo(OBJNAMEPROCEDURE));
    assertThat(antwort.getObjiddocument(), equalTo(OBJIDDOCUMENT));
    assertThat(antwort.getObjnamedocument(), equalTo(OBJNAMEDOCUMENT));
    assertThat(antwort.getObjidcontent(), equalTo(OBJIDCONTENT));
    assertThat(antwort.getObjnamecontent(), equalTo(OBJNAMECONTENT));
  }

  private CreateCompleteProcedureGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateCompleteProcedureGIResponse response = new CreateCompleteProcedureGIResponse();
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
}
