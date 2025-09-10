/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.lesen;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielListObjectTypeReferenz;
import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielObjectTypeReferenz;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadFileGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ReadFileResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;
  private static final String SHORTNAME = "2016 Anträge Firma XY";
  private static final String FILESUBJ = "Alle Anträge der Firma XY aus dem Jahr 2016";
  private static final String OBJTERMS = "Firma XY; Anträge; Anträge 2016";
  private static final String ACCDEF =
      "Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)";
  private static final String FILEOUOBJ = "COO.1.2300.1.847";
  private static final String PROCEDUREACCDEF =
      "Zugriffsdefinition für Schriftgutobjekte der Organisationseinheit";
  private static final String OBJNAME = "0010 A20 011-2016 Anträge Firma XY-4";
  private static final String APENTRY = "COO.1.2301.1.683";

  private ReadFileResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadFileResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws IOException {

    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  public Exchange erzeugeExchangeObjekt(ReadFileGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  public ReadFileGIResponse erzeugeDmsAntwortOhneFehler() {
    ReadFileGIResponse response = new ReadFileGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setShortname(SHORTNAME);
    response.setFilesubj(FILESUBJ);
    response.setObjterms(OBJTERMS);
    response.setAccdef(ACCDEF);
    response.setFileouobj(FILEOUOBJ);
    response.setProcedureaccdef(PROCEDUREACCDEF);
    response.setObjname(OBJNAME);
    response.setApentry(APENTRY);

    ArrayOfLHMBAI151700GIObjectType listObjectType;
    listObjectType =
        erzeugeBeispielListObjectTypeReferenz(
            erzeugeBeispielObjectTypeReferenz("Testeingang", "COO.10.3040.3.2387"));
    response.setGiobjecttype(listObjectType);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadFileResponseDTO antwort = mapper.createParser(body).readValueAs(ReadFileResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getShortname(), equalTo(SHORTNAME));
    assertThat(antwort.getFilesubj(), equalTo(FILESUBJ));
    assertThat(antwort.getObjterms(), equalTo(OBJTERMS));
    assertThat(antwort.getAccdef(), equalTo(ACCDEF));
    assertThat(antwort.getFileouobj(), equalTo(FILEOUOBJ));
    assertThat(antwort.getProcedureaccdef(), equalTo(PROCEDUREACCDEF));
    assertThat(antwort.getObjname(), equalTo(OBJNAME));
    assertThat(antwort.getApentry(), equalTo(APENTRY));
    assertEquals(antwort.getGiobjecttype(), erzeugeBeispielObjectReferenz());
  }

  private List<Objektreferenz> erzeugeBeispielObjectReferenz() {
    return Collections.singletonList(
        Objektreferenz.builder().objname("Testeingang").objaddress("COO.10.3040.3.2387").build());
  }
}
