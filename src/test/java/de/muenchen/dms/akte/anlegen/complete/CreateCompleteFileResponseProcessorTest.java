package de.muenchen.dms.akte.anlegen.complete;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateCompleteFileGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class CreateCompleteFileResponseProcessorTest {

  private static final String OBJIDFILE = "COO.1234.1234.1.1234";
  private static final String OBJNAMEFILE = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDPROCEDURE = "COO.1.2301.1.1042406";
  private static final String OBJNAMEPROCEDURE = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDDOCUMENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMEDOCUMENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String OBJIDCONTENT = "COO.1.2301.1.1042406";
  private static final String OBJNAMECONTENT = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final int NO_ERROR_CODE = 0;

  private CreateCompleteFileResponseProcessor createCompleteFileResponseProcessor;

  @BeforeEach
  void setUp() {
    createCompleteFileResponseProcessor = new CreateCompleteFileResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    createCompleteFileResponseProcessor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(CreateCompleteFileGIResponse response) {
    CamelContext ctx = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(ctx);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws Exception {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    CreateCompleteFileResponseDTO antwort =
        mapper.createParser(body).readValueAs(CreateCompleteFileResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getObjidfile(), equalTo(OBJIDFILE));
    assertThat(antwort.getObjnamefile(), equalTo(OBJNAMEFILE));
    assertThat(antwort.getObjidprocedure(), equalTo(OBJIDPROCEDURE));
    assertThat(antwort.getObjnameprocedure(), equalTo(OBJNAMEPROCEDURE));
    assertThat(antwort.getObjiddocument(), equalTo(OBJIDDOCUMENT));
    assertThat(antwort.getObjnamedocument(), equalTo(OBJNAMEDOCUMENT));
    assertThat(antwort.getObjidcontent(), equalTo(OBJIDCONTENT));
    assertThat(antwort.getObjnamecontent(), equalTo(OBJNAMECONTENT));
  }

  private CreateCompleteFileGIResponse erzeugeDmsAntwortOhneFehler() {
    CreateCompleteFileGIResponse response = new CreateCompleteFileGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjidfile(OBJIDFILE);
    response.setObjnamefile(OBJNAMEFILE);
    response.setObjidprocedure(OBJIDPROCEDURE);
    response.setObjnameprocedure(OBJNAMEPROCEDURE);
    response.setObjiddocument(OBJIDDOCUMENT);
    response.setObjnamedocument(OBJNAMEDOCUMENT);
    response.setObjidcontent(OBJIDCONTENT);
    response.setObjnamecontent(OBJNAMECONTENT);
    return response;
  }
}
