/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.model;

/**
 * Nutze diesen Ausnahmefehler an Stellen, an denen es sicher ist, dass der Status nur durch einen
 * Fehler während der Programmierung entstanden sein kann. Beispiele sind neue ENUM-Werte ohne
 * Anpassung der sie auswerteten switch-Blöcke etc.
 */
public class DmsDeveloperException extends RuntimeException {
  public DmsDeveloperException(String message) {
    super(message);
  }

  public DmsDeveloperException(String message, Throwable throwable) {
    super(message, throwable);
  }
}
