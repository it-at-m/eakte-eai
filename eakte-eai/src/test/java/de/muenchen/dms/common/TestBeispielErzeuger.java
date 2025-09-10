package de.muenchen.dms.common;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700BusinessObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAddresseeType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700BusinessObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAddresseeType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIObjectType;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.muenchen.dms.common.model.DmsErrorResponse;
import de.muenchen.dms.common.model.DmsObjektResponse;
import de.muenchen.dms.common.util.JacksonData;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.support.DefaultExchange;
import org.apache.hc.client5.http.entity.mime.MultipartEntityBuilder;
import org.apache.hc.core5.http.ContentType;
import org.apache.hc.core5.http.HttpEntity;
import org.springframework.core.io.ClassPathResource;

public class TestBeispielErzeuger {
  private static final String PART_SCHRIFTSTUECKE = "schriftstuecke";
  private static final String PART_KERNNACHRICHT = "ausgang";

  public static Exchange erzeugeExchangeObjekt(Object dto) throws JsonProcessingException {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange = new DefaultExchange(camelContext);

    ObjectMapper mapper = JacksonData.erzeugeObjectMapper();

    if (dto != null) {
      String body = mapper.writeValueAsString(dto);
      exchange.getMessage().setBody(body);
    }

    return exchange;
  }

  public static <T> HttpEntity erzeugeMultipartNachricht(T dto, Class<T> clazz, int anzahlAnhaenge)
      throws IOException {
    String message = wandleDtoZuJsonText(dto, clazz);

    MultipartEntityBuilder multipartEntityBuilder =
        MultipartEntityBuilder.create()
            .addTextBody(PART_KERNNACHRICHT, message, ContentType.APPLICATION_JSON);

    ClassPathResource resource;
    for (int i = 0; i < anzahlAnhaenge; ++i) {
      resource = new ClassPathResource("sample.pdf");
      multipartEntityBuilder =
          multipartEntityBuilder.addBinaryBody(
              PART_SCHRIFTSTUECKE,
              resource.getInputStream(),
              ContentType.APPLICATION_OCTET_STREAM,
              "sample.pdf");
    }
    return multipartEntityBuilder.build();
  }

  public static <T> String wandleDtoZuJsonText(T dto, Class<T> clazz)
      throws JsonProcessingException {
    return JacksonData.erzeugeObjectMapper().writerFor(clazz).writeValueAsString(dto);
  }

  public static DmsObjektResponse wandleJsonStringZuDMSAntwort(String json)
      throws JsonProcessingException {
    return wandleJsonStringZu(json, DmsObjektResponse.class);
  }

  public static DmsErrorResponse wandleJsonStringZuDMSFehler(String json)
      throws JsonProcessingException {
    return wandleJsonStringZu(json, DmsErrorResponse.class);
  }

  public static <T> T wandleJsonStringZu(String json, Class<T> clazz)
      throws JsonProcessingException {
    return JacksonData.erzeugeObjectMapper().readerFor(clazz).readValue(json);
  }

  public static String erzeugeAuthorizationHeader() {
    return "Basic dGVzdHVzZXJkbXNpbmVhaTp0ZXN0cHc=";
  }

  public static TestProcessor erstelleFakeProcessor() {
    return new TestProcessor();
  }

  /**
   * Diese Methode erzeugt eine Liste von beispielhaften Objektreferenzen auf die vorgeblich
   * angelegten Anhänge im Dokumentenmanagementsystem
   *
   * @param start Nummer des ersten Eintrages
   * @param anzahl Anzahl der Einträge
   * @param name Präfix von oder Name aller Objekte
   * @param nameCounter Soll der Name des Objektes die laufende Nummer enthalten?
   * @return Liste von Objektreferenzen
   */
  public static ArrayOfLHMBAI151700GIObjectType erzeugeBeispielObjektreferenzen(
      long start, long anzahl, String name, boolean nameCounter) {
    ArrayOfLHMBAI151700GIObjectType objectType = new ArrayOfLHMBAI151700GIObjectType();

    for (long i = start; i < start + anzahl - 1; ++i) {
      objectType
          .getLHMBAI151700GIObjectType()
          .add(erzeugeBeispielObjektreferenz(i, name, nameCounter));
    }

    return objectType;
  }

  /**
   * Erzeugt eine einzelne Referenz auf einen Anhang
   *
   * @param id Nummer zur Unterscheidung verschiedener Anhänge
   * @return Referenz auf einen Anhang
   */
  public static LHMBAI151700GIObjectType erzeugeBeispielObjektreferenz(
      long id, String name, boolean nameCounter) {
    String kombinierterName = name;

    if (nameCounter) {
      kombinierterName = String.format("%s %d", kombinierterName, id);
    }

    LHMBAI151700GIObjectType objType = new LHMBAI151700GIObjectType();
    objType.setLHMBAI151700Objname(kombinierterName);
    objType.setLHMBAI151700Objaddress(erstelleObjektId(id));
    return objType;
  }

  public static LHMBAI151700GIMetadataType erzeugeBeispielMetadatareferenz(
      String objaddress,
      String filename,
      String fileextension,
      String objclass,
      String contsize,
      ArrayOfstring objcreatedby,
      XMLGregorianCalendar objcreatedat,
      ArrayOfstring objchangedby,
      XMLGregorianCalendar objmodifiedat) {

    LHMBAI151700GIMetadataType metaType = new LHMBAI151700GIMetadataType();

    metaType.setLHMBAI151700Objaddress(objaddress);
    metaType.setLHMBAI151700Filename(filename);
    metaType.setLHMBAI151700Fileextension(fileextension);
    metaType.setLHMBAI151700Objclass(objclass);
    metaType.setLHMBAI151700Contsize(contsize);
    metaType.setLHMBAI151700Objcreatedby(objcreatedby);
    metaType.setLHMBAI151700Objcreatedat(objcreatedat);
    metaType.setLHMBAI151700Objchangedby(objchangedby);
    metaType.setLHMBAI151700Objmodifiedat(objmodifiedat);
    return metaType;
  }

  public static LHMBAI151700GIAddresseeType erzeugeBeispielAddresseeReferenz(
      XMLGregorianCalendar addrdate,
      String addrtransmedia,
      String addrOrgName,
      String addrtitle,
      String addrfirstname,
      String addrname,
      String addrstreet,
      String addrstreetnumber,
      String addrzipcode,
      String addrcity,
      String addrcountry,
      String addremail,
      String addradditional5) {
    LHMBAI151700GIAddresseeType addresseeType = new LHMBAI151700GIAddresseeType();

    addresseeType.setLHMBAI151700Addrdate(addrdate);
    addresseeType.setLHMBAI151700Addrtransmedia(addrtransmedia);
    addresseeType.setLHMBAI151700Addrorgname(addrOrgName);
    addresseeType.setLHMBAI151700Addrtitle(addrtitle);
    addresseeType.setLHMBAI151700Addrfirstname(addrfirstname);
    addresseeType.setLHMBAI151700Addrname(addrname);
    addresseeType.setLHMBAI151700Addrstreet(addrstreet);
    addresseeType.setLHMBAI151700Addrstreetnumber(addrstreetnumber);
    addresseeType.setLHMBAI151700Addrzipcode(addrzipcode);
    addresseeType.setLHMBAI151700Addrcity(addrcity);
    addresseeType.setLHMBAI151700Addrcountry(addrcountry);
    addresseeType.setLHMBAI151700Addremail(addremail);
    addresseeType.setLHMBAI151700Addradditional5(addradditional5);
    return addresseeType;
  }

  public static LHMBAI151700GIObjectType erzeugeBeispielObjectTypeReferenz(
      String name, String objid) {
    LHMBAI151700GIObjectType objectType = new LHMBAI151700GIObjectType();
    objectType.setLHMBAI151700Objname(name);
    objectType.setLHMBAI151700Objaddress(objid);
    return objectType;
  }

  public static LHMBAI151700BusinessObjectType erzeugeBeispielBusinessObjectTypeReferenz(
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
    LHMBAI151700BusinessObjectType businessObjectType = new LHMBAI151700BusinessObjectType();
    businessObjectType.setLHMBAI151700Objname(objname);
    businessObjectType.setLHMBAI151700Objaddress(objaddress);
    businessObjectType.setLHMBAI151700Objclass(objclass);
    businessObjectType.setLHMBAI151700Objcreatedat(objcreatedat);
    businessObjectType.setLHMBAI151700Objcreatedby(objcreatedby);
    businessObjectType.setLHMBAI151700Objmodifiedat(objmodifiedat);
    businessObjectType.setLHMBAI151700Objchangedby(objchangedby);
    businessObjectType.setLHMBAI151700Objowner(objowner);
    businessObjectType.setLHMBAI151700Objowngroup(objou);
    businessObjectType.setLHMBAI151700Fileextension(fileextension);
    businessObjectType.setLHMBAI151700Contsize(contsize);
    return businessObjectType;
  }

  public static ArrayOfLHMBAI151700GIObjectType erzeugeBeispielListObjectTypeReferenz(
      LHMBAI151700GIObjectType objectType) {
    ArrayOfLHMBAI151700GIObjectType listObjectType = new ArrayOfLHMBAI151700GIObjectType();
    listObjectType.getLHMBAI151700GIObjectType().add(objectType);
    return listObjectType;
  }

  public static ArrayOfLHMBAI151700BusinessObjectType erzeugeBeispielListBusinessObjectTypeReferenz(
      LHMBAI151700BusinessObjectType businessObjectType) {
    ArrayOfLHMBAI151700BusinessObjectType listBusinessObjectType =
        new ArrayOfLHMBAI151700BusinessObjectType();
    listBusinessObjectType.getLHMBAI151700BusinessObjectType().add(businessObjectType);
    return listBusinessObjectType;
  }

  public static LHMBAI151700GIAttachmentType erzeugeBeispielAttachmentreferenz(
      String fileName,
      String fileExtention,
      String fileContent,
      String fileContsize,
      ArrayOfstring filesubj) {
    LHMBAI151700GIAttachmentType attachmentType = new LHMBAI151700GIAttachmentType();
    byte[] contentBytes =
        Base64.getMimeEncoder().encode(fileContent.getBytes(StandardCharsets.UTF_8));

    attachmentType.setLHMBAI151700Filename(fileName);
    attachmentType.setLHMBAI151700Fileextension(fileExtention);
    attachmentType.setLHMBAI151700Filecontent(contentBytes);
    attachmentType.setLHMBAI151700Contsize(fileContsize);
    attachmentType.setLHMBAI151700Filesubj(filesubj);
    return attachmentType;
  }

  public static ArrayOfLHMBAI151700GIMetadataType erzeugeBeispielListMetadatareferenz(
      LHMBAI151700GIMetadataType metadata) {
    ArrayOfLHMBAI151700GIMetadataType listmeta = new ArrayOfLHMBAI151700GIMetadataType();
    listmeta.getLHMBAI151700GIMetadataType().add(metadata);
    return listmeta;
  }

  public static ArrayOfLHMBAI151700GIAddresseeType erzeugeBeispielListAddresseeReferenz(
      LHMBAI151700GIAddresseeType addressee) {
    ArrayOfLHMBAI151700GIAddresseeType listAddresse = new ArrayOfLHMBAI151700GIAddresseeType();
    listAddresse.getLHMBAI151700GIAddresseeType().add(addressee);
    return listAddresse;
  }

  public static ArrayOfLHMBAI151700GIAttachmentType erzeugeBeispielListAttachmentreferenz(
      LHMBAI151700GIAttachmentType attachment) {
    ArrayOfLHMBAI151700GIAttachmentType listAttachment = new ArrayOfLHMBAI151700GIAttachmentType();
    listAttachment.getLHMBAI151700GIAttachmentType().add(attachment);
    return listAttachment;
  }

  // COO.2150.9151.1.1206972
  public static String erstelleObjektId(long id) {
    long part1 = id / 1000000000000L;
    long part2 = (id - part1) / 100000000L;
    long part3 = (id - part1 - part2) / 10000000L;
    long part4 = id - part1 - part2 - part3;

    return String.format("COO.%04d.%04d.%1d.%07d", part1, part2, part3, part4);
  }

  public static class TestProcessor implements Processor {

    TestProcessor() {}

    private int anzahlAufrufe = 0;

    @Override
    public void process(Exchange exchange) throws Exception {
      ++anzahlAufrufe;
    }

    public int anzahlAufrufe() {
      return anzahlAufrufe;
    }

    public void reset() {
      anzahlAufrufe = 0;
    }
  }
}
