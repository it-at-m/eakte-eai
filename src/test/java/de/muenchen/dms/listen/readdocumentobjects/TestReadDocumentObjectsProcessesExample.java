package de.muenchen.dms.listen.readdocumentobjects;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadDocumentGIObjectsResponse;
import java.util.Arrays;

public class TestReadDocumentObjectsProcessesExample {
  static ReadDocumentGIObjectsResponse erzeugeAntwort() {
    final ObjectFactory objectFactory = new ObjectFactory();
    final ReadDocumentGIObjectsResponse data = objectFactory.createReadDocumentGIObjectsResponse();
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
                erstelleSoapObjektReferenz("Dokument 1", "COO.2150.9151.1.1207768"),
                erstelleSoapObjektReferenz("Dokument 2", "COO.2150.9151.1.1207769")));
    return result;
  }

  private static LHMBAI151700GIObjectType erstelleSoapObjektReferenz(String objektname, String id) {
    var result = new LHMBAI151700GIObjectType();
    result.setLHMBAI151700Objname(objektname);
    result.setLHMBAI151700Objaddress(id);
    return result;
  }
}
