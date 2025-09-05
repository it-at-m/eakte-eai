/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.akte.lesen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadFileGIResponse;
import de.muenchen.dms.common.util.JacksonData;
import java.time.OffsetDateTime;
import java.util.Arrays;
import javax.xml.datatype.DatatypeConfigurationException;

class TestReadFileExample {

  private TestReadFileExample() {}

  static ReadFileGIResponse erzeugeAntwort() throws DatatypeConfigurationException {
    final ObjectFactory objectFactory = new ObjectFactory();
    final ReadFileGIResponse data = objectFactory.createReadFileGIResponse();
    data.setStatus(0);
    data.setObjname("Akte-4");
    data.setApentry("COOID 12121212");
    data.setFileouobj("COOID ORGA");
    data.setShortname("Akte");
    data.setFilesubj("Betreff");
    data.setProcedureaccdef("Zugriffsdefinitionvorgaenge");
    data.setObjterms("Schlagwort1,; Schlagwort2;");
    data.setAccdef("Zugriffsdefinition");
    data.setFileruntimefrom(JacksonData.toXMLGregorianCalendar(OffsetDateTime.now()));
    data.setFileruntimetill(JacksonData.toXMLGregorianCalendar(OffsetDateTime.now()));
    data.setGiobjecttype(erstelleVorgangsreferenzen());
    return data;
  }

  private static ArrayOfLHMBAI151700GIObjectType erstelleVorgangsreferenzen() {
    ArrayOfLHMBAI151700GIObjectType result = new ArrayOfLHMBAI151700GIObjectType();

    result
        .getLHMBAI151700GIObjectType()
        .addAll(
            Arrays.asList(
                erstelleSoapObjektReferenz("Akte 1 - Vorgang 1", "COO.2150.9151.1.1207768"),
                erstelleSoapObjektReferenz("Akte 1 - Vorgang 2", "COO.2150.9151.1.1207769")));
    return result;
  }

  private static LHMBAI151700GIObjectType erstelleSoapObjektReferenz(String objektname, String id) {
    var result = new LHMBAI151700GIObjectType();
    result.setLHMBAI151700Objname(objektname);
    result.setLHMBAI151700Objaddress(id);
    return result;
  }
}
