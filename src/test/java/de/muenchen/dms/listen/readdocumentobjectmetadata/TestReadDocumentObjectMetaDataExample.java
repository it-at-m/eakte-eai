package de.muenchen.dms.listen.readdocumentobjectmetadata;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700BusinessObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700BusinessObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ObjectFactory;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ReadDocumentGIObjectsMetaDataResponse;
import java.util.Arrays;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

public class TestReadDocumentObjectMetaDataExample {
  private TestReadDocumentObjectMetaDataExample() {}

  static ReadDocumentGIObjectsMetaDataResponse erzeugeAntwort() {
    final ObjectFactory objectFactory = new ObjectFactory();
    final ReadDocumentGIObjectsMetaDataResponse data =
        objectFactory.createReadDocumentGIObjectsMetaDataResponse();
    data.setStatus(0);

    data.setBusinessobjecttype(erstelleVorgangsreferenzen());
    return data;
  }

  private static ArrayOfLHMBAI151700BusinessObjectType erstelleVorgangsreferenzen() {
    ArrayOfLHMBAI151700BusinessObjectType result = new ArrayOfLHMBAI151700BusinessObjectType();

    XMLGregorianCalendar objcreatedat = null;
    XMLGregorianCalendar objmodifiedat = null;

    try {
      objcreatedat =
          DatatypeFactory.newInstance().newXMLGregorianCalendar("2016-05-01T22:00:00.000Z");
      objmodifiedat =
          DatatypeFactory.newInstance().newXMLGregorianCalendar("2024-10-11T22:00:00.000Z");
    } catch (DatatypeConfigurationException e) {
      e.printStackTrace();
    }

    ArrayOfstring objcreatedby = new ArrayOfstring();
    objcreatedby.getString().add("lhm/max.mustermann");

    ArrayOfstring objchangedby = new ArrayOfstring();
    objcreatedby.getString().add("lhm/erika.musterfrau");

    result
        .getLHMBAI151700BusinessObjectType()
        .addAll(
            Arrays.asList(
                erstelleSoapObjektReferenz(
                    "0002 A20 10.1.1-1",
                    "COO.1.2301.1.1042465",
                    "Textdokument",
                    objcreatedat,
                    objcreatedby,
                    objmodifiedat,
                    objchangedby,
                    "Mustermann, Max",
                    "SG A1 0001 (Sachgebiet A1 0001)",
                    "txt",
                    "5"),
                erstelleSoapObjektReferenz(
                    "0002 A20 10.1.1-2",
                    "COO.1.2301.1.3579",
                    "PDF",
                    objcreatedat,
                    objcreatedby,
                    objmodifiedat,
                    objchangedby,
                    "Musterfrau, Erika",
                    "SG A1 0002 (Sachgebiet A1 0002)",
                    "pdf",
                    "7")));
    return result;
  }

  private static LHMBAI151700BusinessObjectType erstelleSoapObjektReferenz(
      String objname,
      String objaddress,
      String objclass,
      XMLGregorianCalendar objcreatedat,
      ArrayOfstring objcreatedby,
      XMLGregorianCalendar objmodifiedat,
      ArrayOfstring objchangedby,
      String objowner,
      String objou,
      String fileextension,
      String contsize) {
    var result = new LHMBAI151700BusinessObjectType();
    result.setLHMBAI151700Objname(objname);
    result.setLHMBAI151700Objaddress(objaddress);
    result.setLHMBAI151700Objclass(objclass);
    result.setLHMBAI151700Objcreatedat(objcreatedat);
    result.setLHMBAI151700Objcreatedby(objcreatedby);
    result.setLHMBAI151700Objmodifiedat(objmodifiedat);
    result.setLHMBAI151700Objchangedby(objchangedby);
    result.setLHMBAI151700Objowner(objowner);
    result.setLHMBAI151700Objowngroup(objou);
    result.setLHMBAI151700Fileextension(fileextension);
    result.setLHMBAI151700Contsize(contsize);
    return result;
  }
}
