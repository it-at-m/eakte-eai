/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.aendern;

import de.muenchen.dms.common.model.Aussonderungsart;
import de.muenchen.dms.common.model.Frist;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/** Diese Klasse erstellt Beispiel-Aktendaten für die Testfälle */
public class TestUpdateFileExample {
  public static UpdateFileDTO erzeugeFehlerlosMitUmlauten() {
    // Für den Fall, dass jemand ein Datum ändert, wird das Offset am Datum berechnet
    LocalDateTime laufzeitBeginnLocal = LocalDateTime.of(2023, 1, 1, 12, 17, 0);
    ZoneOffset laufzeitBeginnZoneOffset =
        ZoneOffset.systemDefault().getRules().getOffset(laufzeitBeginnLocal);

    LocalDateTime laufzeitEndeLocal = LocalDateTime.of(2023, 11, 7, 9, 13, 0);
    ZoneOffset laufzeitEndeZoneOffset =
        ZoneOffset.systemDefault().getRules().getOffset(laufzeitEndeLocal);

    return UpdateFileDTO.builder()
        .shortname("2016 Anträge Firma XY")
        .filesubj("Testdurchführung erfolgt")
        .procedureaccdef("Zugriffsdefinition für Vorgangsdaten (allgemein lesbar)")
        .objterms("Testschlagwort")
        .fileruntimefrom(OffsetDateTime.of(laufzeitBeginnLocal, laufzeitBeginnZoneOffset))
        .fileruntimetill(OffsetDateTime.of(laufzeitEndeLocal, laufzeitEndeZoneOffset))
        .filearchivetimeOffltdeadline(Frist.JAHR10.getValue())
        .filelifetimeOffltdeadline(Frist.JAHR5.getValue())
        .offltdispstate(Aussonderungsart.DISPSTATE_ARCHIVE.name())
        .build();
  }
}
