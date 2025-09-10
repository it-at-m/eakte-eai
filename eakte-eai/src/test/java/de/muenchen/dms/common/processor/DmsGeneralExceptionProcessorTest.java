/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2022
 */
package de.muenchen.dms.common.processor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import de.muenchen.dms.common.route.exception.general.DmsGeneralExceptionProcessor;
import java.util.HashMap;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.http.base.HttpOperationFailedException;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.cxf.transport.http.HTTPException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * GeneralExceptionProcessor
 *
 * @author ext.dl.wenger
 */
class DmsGeneralExceptionProcessorTest /*extends AbstractProcessorTest*/ {

  /**
   * Test: HttpOperationFailedException
   *
   * @throws Exception
   */
  private DmsGeneralExceptionProcessor processor;

  private Exchange exchange;
  private CamelContext camelContext;

  @BeforeEach
  void setUp() {
    // Initialize the processor and exchange before each test
    processor = new DmsGeneralExceptionProcessor();
    camelContext = new DefaultCamelContext();
    exchange =
        new DefaultExchange(camelContext); // Mock or provide a proper Camel context if needed
  }

  @Test
  void testProcessWithHttpOperationFailedException() throws Exception {
    // Create a mock HttpOperationFailedException
    HttpOperationFailedException exception = mock(HttpOperationFailedException.class);

    when(exception.getHttpResponseCode()).thenReturn(401);
    when(exception.getHttpResponseStatus()).thenReturn("Unauthorized");
    when(exception.getResponseHeaders()).thenReturn(new HashMap<>());

    exchange.setProperty(Exchange.EXCEPTION_CAUGHT, exception);

    processor.process(exchange);
    assertEquals(401, exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE));
    assertNotNull(exchange.getMessage().getBody());
  }

  @Test
  void testProcessWithHTTPException() throws Exception {
    // Create a mock HTTPException
    HTTPException exception = mock(HTTPException.class);

    when(exception.getResponseCode()).thenReturn(403);
    when(exception.getResponseMessage()).thenReturn("Forbidden");

    Throwable throwable = mock(Throwable.class);
    when(throwable.getCause()).thenReturn(exception);

    exchange.setProperty(Exchange.EXCEPTION_CAUGHT, throwable);

    processor.process(exchange);

    assertEquals(403, exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE));
    assertNotNull(exchange.getMessage().getBody());
  }

  @Test
  void testOtherException() throws Exception {
    // Create a mock general exception (other than HTTPException or HttpOperationFailedException)
    Throwable throwable = mock(Throwable.class);
    when(throwable.getMessage()).thenReturn("Generic Error");

    exchange.setProperty(Exchange.EXCEPTION_CAUGHT, throwable);

    processor.process(exchange);

    assertEquals(500, exchange.getMessage().getHeader(Exchange.HTTP_RESPONSE_CODE));
    assertNotNull(exchange.getMessage().getBody());
  }
}
