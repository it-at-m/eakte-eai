/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.aendern;

import de.muenchen.dms.common.model.Aussonderungsart;
import de.muenchen.dms.common.model.Frist;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;

/** Diese Klasse erstellt Beispiel-Vorgangsdaten für die Testfälle */
public class TestUpdateProcedureExample {

  public static UpdateProcedureDTO erzeugeFehlerlosMitUmlauten() {
    final LocalDateTime fileRuntimeFromLocal = LocalDateTime.of(2023, 1, 1, 12, 17, 0);
    final ZoneOffset fileRuntimeFromZoneOffset =
        ZoneId.systemDefault().getRules().getOffset(fileRuntimeFromLocal);

    final LocalDateTime fileRuntimetillLocal = LocalDateTime.of(2023, 11, 7, 9, 13, 0);
    final ZoneOffset fileRuntimetillZoneOffset =
        ZoneId.systemDefault().getRules().getOffset(fileRuntimetillLocal);

    return UpdateProcedureDTO.builder()
        .shortname("Anfrage Baugenehmigung vom 23.10.2016")
        .filesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung vom 23.10.2016")
        .objterms("Testschlagwort")
        .fileruntimefrom(OffsetDateTime.of(fileRuntimeFromLocal, fileRuntimeFromZoneOffset))
        .fileruntimetill(OffsetDateTime.of(fileRuntimetillLocal, fileRuntimetillZoneOffset))
        .filearchivetimeOffltdeadline(Frist.JAHR10.getValue())
        .filelifetimeOffltdeadline(Frist.JAHR5.getValue())
        .offltdispstate(Aussonderungsart.DISPSTATE_ARCHIVE.name())
        .procremark("Rückmeldung erhalten am 25.10.2016")
        .filetype("Elektronisch")
        .accdef("Zugriffsdefinition für Schriftgutobjekte (allgemein lesbar)")
        .build();
  }
}
