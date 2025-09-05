/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.lesen;

import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielListObjectTypeReferenz;
import static de.muenchen.dms.common.TestBeispielErzeuger.erzeugeBeispielObjectTypeReferenz;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.Objektreferenz;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

class ReadProcedureResponseProcessorTest {

  private static final int NO_ERROR_CODE = 0;
  private static final String OBJNAME = "Anfrage Baugenehmigung (0010 A20 011-4-0006)";
  private static final String REFERREDNUMBER = "COO.1.2301.1.1042406";
  private static final String SHORTNAME = "Anfrage Baugenehmigung";
  private static final String OBJDOCSTATE = "In Bearbeitung";
  private static final String BOSTATE = "Erstellt";
  private static final String OBJOWNGROUP = "SG A1 0001 (Sachgebiet A1 0001)";
  private static final String FILESUBJ = "Anfrage Firma Mustermann bezüglich Baugenehmigung";
  private static final String PROCREMARK = "Warte auf Rückmeldung";
  private static final String PROCCOSTCENTER = "Kostenstelle A38";
  private static final String FILETYPE = "Elektronisch";
  private static final String OBJTERMS = "Firma XY; Anträge; Anträge 2016";
  private static final String ACCDEF =
      "Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)";
  private static final XMLGregorianCalendar FILERUNTIMEFROM = null;
  private static final XMLGregorianCalendar FILERUNTIMETILL = null;

  private ReadProcedureResponseProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new ReadProcedureResponseProcessor();
  }

  @Test
  void testeErgebnisOhneFehlermeldung() throws Exception {

    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    processor.process(exchange);

    pruefeEAIAntwort(exchange);
  }

  public Exchange erzeugeExchangeObjekt(ReadProcedureGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  public ReadProcedureGIResponse erzeugeDmsAntwortOhneFehler() {
    ReadProcedureGIResponse response = new ReadProcedureGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setObjname(OBJNAME);
    response.setReferrednumber(REFERREDNUMBER);
    response.setShortname(SHORTNAME);
    response.setObjdocstate(OBJDOCSTATE);
    response.setBostate(BOSTATE);
    response.setObjowngroup(OBJOWNGROUP);
    response.setFilesubj(FILESUBJ);
    response.setProcremark(PROCREMARK);
    response.setProccostcenter(PROCCOSTCENTER);
    response.setFiletype(FILETYPE);
    response.setObjterms(OBJTERMS);

    ArrayOfLHMBAI151700GIObjectType listObjectType;
    listObjectType =
        erzeugeBeispielListObjectTypeReferenz(
            erzeugeBeispielObjectTypeReferenz("Testeingang", "COO.10.3040.3.2387"));
    response.setGiobjecttype(listObjectType);

    response.setAccdef(ACCDEF);
    response.setFileruntimefrom(FILERUNTIMEFROM);
    response.setFileruntimetill(FILERUNTIMETILL);
    return response;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {
    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadProcedureResponseDTO antwort =
        mapper.createParser(body).readValueAs(ReadProcedureResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(antwort.getObjname(), equalTo(OBJNAME));
    assertThat(antwort.getReferrednumber(), equalTo(REFERREDNUMBER));
    assertThat(antwort.getShortname(), equalTo(SHORTNAME));
    assertThat(antwort.getObjdocstate(), equalTo(OBJDOCSTATE));
    assertThat(antwort.getBostate(), equalTo(BOSTATE));
    assertThat(antwort.getObjowngroup(), equalTo(OBJOWNGROUP));
    assertThat(antwort.getFilesubj(), equalTo(FILESUBJ));
    assertThat(antwort.getProcremark(), equalTo(PROCREMARK));
    assertThat(antwort.getFiletype(), equalTo(FILETYPE));
    assertThat(antwort.getObjterms(), equalTo(OBJTERMS));
    assertEquals(antwort.getGiobjecttype(), erzeugeBeispielObjectReferenz());
    assertThat(antwort.getAccdef(), equalTo(ACCDEF));
    assertThat(antwort.getFileruntimefrom(), equalTo(FILERUNTIMEFROM));
    assertThat(antwort.getFileruntimetill(), equalTo(FILERUNTIMETILL));
  }

  private List<Objektreferenz> erzeugeBeispielObjectReferenz() {
    return Collections.singletonList(
        Objektreferenz.builder().objname("Testeingang").objaddress("COO.10.3040.3.2387").build());
  }
}
