/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import de.muenchen.dms.common.model.Objektreferenz;
import java.util.Collections;
import java.util.List;

public class TestObjektreferenzen {
  private TestObjektreferenzen() {}

  public static void pruefeGleichheitVonSoapUndRestReferenzen(
      ArrayOfLHMBAI151700GIObjectType soapResponse, List<Objektreferenz> restResponse) {

    List<LHMBAI151700GIObjectType> soapReferenzen =
        soapResponse == null
            ? Collections.emptyList()
            : soapResponse.getLHMBAI151700GIObjectType() == null
                ? Collections.emptyList()
                : soapResponse.getLHMBAI151700GIObjectType();

    List<Objektreferenz> restReferenzen =
        restResponse == null ? Collections.emptyList() : restResponse;

    assertEquals(
        soapReferenzen.size(),
        restReferenzen.size(),
        "Unterschiedliche Anzahl an Objektreferenzen");

    for (var soapReferenz : soapReferenzen) {
      assertThat(restReferenzen, hasItem(wandleSOAPAntwortInRESTAntwort(soapReferenz)));
    }

    if (soapResponse == null && !(restResponse == null || restResponse.isEmpty())) {
      fail("Keine Objekte über SOAP erhalten, jedoch über REST zurückgegeben.");
    }
  }

  private static Objektreferenz wandleSOAPAntwortInRESTAntwort(
      LHMBAI151700GIObjectType soapObjektreferenz) {
    return Objektreferenz.builder()
        .objname(soapObjektreferenz.getLHMBAI151700Objname())
        .objaddress(soapObjektreferenz.getLHMBAI151700Objaddress())
        .build();
  }
}
