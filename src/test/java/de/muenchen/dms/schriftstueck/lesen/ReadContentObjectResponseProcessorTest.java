/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.schriftstueck.lesen;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadContentObjectGIResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.AttachmentType;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

class ReadContentObjectResponseProcessorTest {
  private static final int NO_ERROR_CODE = 0;
  private static final String BEISPIEL_REFERREDNUMBER = "COO.1.2.3.4";

  private ReadContentObjectResponseProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new ReadContentObjectResponseProcessor();
  }

  @Test
  @DisplayName("Teste die Verarbeitung eines Ergebnisses des DMS ohne Fehlermeldung")
  void testeErgebnisOhneFehlermeldung() throws Exception {
    // Gegeben
    Exchange exchange = erzeugeExchangeObjekt(erzeugeDmsAntwortOhneFehler());

    // Wenn
    processor.process(exchange);

    // Dann
    pruefeEAIAntwort(exchange);
  }

  private Exchange erzeugeExchangeObjekt(ReadContentObjectGIResponse response) {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);
    exchange.getIn().setBody(response);
    return exchange;
  }

  private void pruefeEAIAntwort(Exchange exchange) throws IOException {

    String body = exchange.getIn().getBody(String.class);
    ObjectMapper mapper = new ObjectMapper();
    ReadContentObjectResponseDTO antwort =
        mapper.createParser(body).readValueAs(ReadContentObjectResponseDTO.class);

    assertThat(exchange.getIn().getHeader(Exchange.HTTP_RESPONSE_CODE), is(HttpStatus.OK.value()));
    assertThat(
        exchange.getIn().getHeader(Exchange.CONTENT_TYPE), is(MediaType.APPLICATION_JSON_VALUE));
    Assertions.assertEquals(antwort.getReferrednumber(), BEISPIEL_REFERREDNUMBER);
    Assertions.assertEquals(antwort.getGiattachmenttype(), erzeugeBeispielAttachmentType());
  }

  private ReadContentObjectGIResponse erzeugeDmsAntwortOhneFehler() {
    ReadContentObjectGIResponse response = new ReadContentObjectGIResponse();
    response.setStatus(NO_ERROR_CODE);
    response.setErrormessage(null);
    response.setGiattachmenttype(erzeugeDmsAntwortAttachment());
    response.setReferrednumber(BEISPIEL_REFERREDNUMBER);
    return response;
  }

  private LHMBAI151700GIAttachmentType erzeugeDmsAntwortAttachment() {
    var content = "Test Content";
    byte[] contentBytes = Base64.getMimeEncoder().encode(content.getBytes(StandardCharsets.UTF_8));
    var attachmentType = new LHMBAI151700GIAttachmentType();
    attachmentType.setLHMBAI151700Filename("Dateiname");
    attachmentType.setLHMBAI151700Filecontent(contentBytes);
    attachmentType.setLHMBAI151700Fileextension(".txt");
    attachmentType.setLHMBAI151700Contsize("12");
    attachmentType.setLHMBAI151700Filesubj(null);
    return attachmentType;
  }

  private AttachmentType erzeugeBeispielAttachmentType() {
    var content = "Test Content";
    byte[] contentBytes = Base64.getMimeEncoder().encode(content.getBytes(StandardCharsets.UTF_8));
    String base64String = Base64.getEncoder().encodeToString(contentBytes);

    return AttachmentType.builder()
        .fileName("Dateiname")
        .fileExtention(".txt")
        .fileContent(base64String)
        .fileContsize("12")
        .filesubj(null)
        .build();
  }
}
