/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route;

import de.muenchen.dms.common.route.exception.DmsExceptionHandler;
import de.muenchen.dms.common.route.exception.bad_credentials.DmsBadCredentialsExceptionHandler;
import de.muenchen.dms.common.route.exception.general.DmsGeneralExceptionHandler;
import de.muenchen.dms.common.route.exception.not_found.DmsEndpointNotFoundExceptionHandler;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.OnExceptionDefinition;

/** Use this route builder for all DMS specific routes It handles exceptions and further more. */
public abstract class DmsRouteBuilder extends RouteBuilder {
  private final DmsExceptionHandler[] exceptionHandlers;

  /**
   * Constructor
   *
   * <p>Use this constructor if you want to handle other exceptions than the default ones.
   *
   * @param exceptionHandler the exception handler
   */
  protected DmsRouteBuilder(DmsExceptionHandler... exceptionHandler) {
    this.exceptionHandlers = exceptionHandler;
  }

  /** Constructor. Use the default exception handlers. */
  protected DmsRouteBuilder() {
    exceptionHandlers =
        new DmsExceptionHandler[] {
          new DmsGeneralExceptionHandler(),
          new DmsEndpointNotFoundExceptionHandler(),
          new DmsBadCredentialsExceptionHandler()
        };
  }

  /**
   * This method is closed in order to prevent child classes to accidentally overwrite the exception
   * handling
   *
   * @throws Exception While setting up a route any exception could occur
   */
  @Override
  public final void configure() throws Exception {
    for (DmsExceptionHandler exceptionHandler : exceptionHandlers) {
      exceptionHandler.handleException(this);
    }

    route();
  }

  /**
   * This method is closed in order to prevent child classes to call exception handlers and wrong
   * places. Exception handling is only allowed before any route has been declared.
   */
  @Override
  public final OnExceptionDefinition onException(Class<? extends Throwable> exception) {
    return super.onException(exception);
  }

  /**
   * Overwrite this method in order to define your own route.
   *
   * @throws Exception Routes may throw any kind of exception
   */
  public abstract void route() throws Exception;
}
