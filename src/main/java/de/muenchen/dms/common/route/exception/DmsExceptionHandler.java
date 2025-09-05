/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route.exception;

import org.apache.camel.builder.RouteBuilder;

/** Marks exceptions that should be handled by all routes that are created by a #DmsRouteBuilder */
public interface DmsExceptionHandler {
  void handleException(RouteBuilder builder);
}
