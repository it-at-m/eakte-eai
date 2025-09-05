package de.muenchen.dms.common.route.exception.general;

/**
 * Klasse für generelle Ausnahmefehler, die tendenziell zum vollständigen Abbruch aller Aktivitäten
 * führen soll.
 */
public class DmsGeneralExecutionException extends RuntimeException {

  /** Konstruiert den Ausnahmefehler */
  public DmsGeneralExecutionException() {
    super();
  }

  /**
   * Konstruiert den Ausnahmefehler
   *
   * @param message erläuternde Fehlermeldung
   */
  public DmsGeneralExecutionException(String message) {
    super(message);
  }

  /**
   * Konstruiert den Ausnahmefehler
   *
   * @param message erläuternde Fehlermeldung
   * @param cause vorhergehender Ausnahmefehler, der den zu erzeugenden Ausnahmefehler verursacht
   *     hat.
   */
  public DmsGeneralExecutionException(String message, Throwable cause) {
    super(message, cause);
  }

  /**
   * Konstruiert den Ausnahmefehler
   *
   * @param cause vorhergehender Ausnahmefehler, der den zu erzeugenden Ausnahmefehler verursacht
   *     hat.
   */
  public DmsGeneralExecutionException(Throwable cause) {
    super(cause);
  }

  /**
   * Konstruiert den Ausnahmefehler
   *
   * @param message erläuternde Fehlermeldung
   * @param cause vorhergehender Ausnahmefehler, der den zu erzeugenden Ausnahmefehler verursacht
   *     hat.
   * @param enableSuppression Soll der verursachende Ausnahmefehler unterdrückt werden?
   * @param writableStackTrace Soll der Stacktrace schreibbar sein?
   */
  protected DmsGeneralExecutionException(
      String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
    super(message, cause, enableSuppression, writableStackTrace);
  }
}
