package de.muenchen.dms.listen.readprocedureobjects;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadProcedureGIObjectsResponse;
import java.util.Arrays;

public class TestReadprocedureobjectsProcessesExample {

  static ReadProcedureGIObjectsResponse erzeugeAntwort() {
    final ObjectFactory objectFactory = new ObjectFactory();
    final ReadProcedureGIObjectsResponse data =
        objectFactory.createReadProcedureGIObjectsResponse();
    data.setStatus(0);

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
