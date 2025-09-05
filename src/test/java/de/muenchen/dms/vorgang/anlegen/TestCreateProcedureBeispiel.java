/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/** Diese Klasse erstellt Beispiel-Vorgangsdaten für die Testfälle */
public class TestCreateProcedureBeispiel {
  public static CreateProcedureDTO erzeugeFehlerlosMitUmlauten() {
    final LocalDateTime laufzeitBeginnLocal = LocalDateTime.of(2023, 1, 1, 12, 17, 0);
    final ZoneOffset laufzeitBeginnZoneOffset =
        ZoneId.systemDefault().getRules().getOffset(laufzeitBeginnLocal);

    final LocalDateTime laufzeitEndeLocal = LocalDateTime.of(2023, 11, 7, 9, 13, 0);
    final ZoneOffset laufzeitEndeZoneOffset =
        ZoneId.systemDefault().getRules().getOffset(laufzeitEndeLocal);

    return CreateProcedureDTO.builder()
        .referrednumber("1")
        .shortname("2016 Anträge Firma XY")
        .procremark("Warte auf Rückmeldung")
        .filetype("Elektronisch")
        .filesubj("Testdurchführung erfolgt")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .objterms("Testschlagwort")
        .fileruntimefrom(OffsetDateTime.of(laufzeitBeginnLocal, laufzeitBeginnZoneOffset))
        .fileruntimetill(OffsetDateTime.of(laufzeitEndeLocal, laufzeitEndeZoneOffset))
        .build();
  }
}
