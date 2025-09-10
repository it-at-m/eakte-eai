package de.muenchen.dms.test;

import jakarta.activation.DataHandler;
import java.util.HashMap;
import java.util.Map;

public class TestDateianhaenge {
  static final String MUSTER_ANHANG_DATEINAME = "anhang_%d.txt";
  static final String MUSTER_ANHANG_INHALT = "inhalt %d";

  private TestDateianhaenge() {}

  /**
   * Erzeuge eine bestimmte Anzahl von Anhaengen.
   *
   * @param anzahl Anzahl von Anhaengen
   * @return Map mit Dateianhängen
   */
  public static Map<String, DataHandler> erzeugeMehrere(int anzahl) {
    Map<String, DataHandler> anhaenge = new HashMap<>();
    String dateiname;
    String inhalt;

    for (int i = 0; i < anzahl; ++i) {
      dateiname = String.format(MUSTER_ANHANG_DATEINAME, i);
      inhalt = String.format(MUSTER_ANHANG_INHALT, i);
      anhaenge.put(dateiname, erzeuge(dateiname, inhalt));
    }
    return anhaenge;
  }

  /**
   * Erzeuge einen einzelnen Dateianhang
   *
   * @param name der Name des Dateianhangs
   * @param dateiinhalt der Inhalt der Datei
   * @return ein Dateianhang
   */
  public static DataHandler erzeuge(String name, String dateiinhalt) {
    return new DataHandler(new TestStringDataSource(name, dateiinhalt));
  }
}
