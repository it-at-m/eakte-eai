/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.anlegen;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/** Diese Klasse erstellt Beispiel-Aktendaten für die Testfälle */
public class TestAkteAnlegenBeispiel {
  public static CreateFileDTO erzeugeFehlerlosMitUmlauten() {
    return CreateFileDTO.builder()
        .apentry("1")
        .shortname("2016 Anträge Firma XY")
        .fileouobj("COO.1232.3453.2.8473824")
        .filesubj("Testdurchführung erfolgt")
        .accdef("Zugriffsdefinition für Vorgangsdaten (allgemein lesbar)")
        .procedureaccdef("Zugriffsdefinition für Vorgangsdaten (allgemein lesbar)")
        .objterms("Testschlagwort")
        .fileruntimefrom(OffsetDateTime.of(2023, 1, 1, 12, 17, 0, 0, ZoneOffset.ofHours(1)))
        .fileruntimetill(OffsetDateTime.of(2023, 11, 7, 9, 13, 0, 0, ZoneOffset.ofHours(1)))
        .build();
  }
}
