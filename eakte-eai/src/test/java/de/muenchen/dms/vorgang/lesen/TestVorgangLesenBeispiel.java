/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIResponse;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.List;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TestVorgangLesenBeispiel {
  private TestVorgangLesenBeispiel() {}

  static ReadProcedureGIResponse erzeugeAntwort() throws DatatypeConfigurationException {
    final ObjectFactory objectFactory = new ObjectFactory();
    final ReadProcedureGIResponse data = objectFactory.createReadProcedureGIResponse();
    final XMLGregorianCalendar laufzeitBeginn =
        DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(
                GregorianCalendar.from(
                    ZonedDateTime.of(2016, 10, 26, 18, 16, 29, 0, ZoneId.systemDefault())));

    final XMLGregorianCalendar laufzeitEnde =
        DatatypeFactory.newInstance()
            .newXMLGregorianCalendar(
                GregorianCalendar.from(
                    ZonedDateTime.of(2023, 10, 30, 18, 16, 29, 0, ZoneId.systemDefault())));

    data.setObjterms("Firma XY; Anträge; Anträge 2016");
    data.setShortname("Anfrage Baugenehmigung");
    data.setObjname("Anfrage Baugenehmigung (0010 A20 011-4-0006)");
    data.setFileruntimefrom(laufzeitBeginn);
    data.setFileruntimetill(laufzeitEnde);
    data.setFilesubj("Anfrage Firma Mustermann bezüglich Baugenehmigung");
    data.setAccdef("Zugriffsdefinition für Vorgangsdaten (allgemein lesbar)");
    data.setReferrednumber("COO.1.2301.1.1042406");
    data.setObjdocstate("In Bearbeitung");
    data.setObjowngroup("SG A1 0001 (Sachgebiet A1 0001)");
    data.setProcremark("Warte auf Rückmeldung");
    data.setProccostcenter("Kostenstelle A38");
    data.setFiletype("Elektronisch");
    data.setBostate("Erstellt");

    List<LHMBAI151700GIObjectType> dokumentListe =
        objectFactory.createArrayOfLHMBAI151700GIObjectType().getLHMBAI151700GIObjectType();
    dokumentListe.add(erzeugeObjectType("1", "Dokument 1"));
    dokumentListe.add(erzeugeObjectType("2", "Dokument 2"));
    dokumentListe.add(erzeugeObjectType("3", "Dokument 3"));

    return data;
  }

  private static LHMBAI151700GIObjectType erzeugeObjectType(String id, String name) {
    final ObjectFactory objectFactory = new ObjectFactory();
    LHMBAI151700GIObjectType objectType = objectFactory.createLHMBAI151700GIObjectType();
    objectType.setLHMBAI151700Objaddress(id);
    objectType.setLHMBAI151700Objname(name);
    return objectType;
  }
}
