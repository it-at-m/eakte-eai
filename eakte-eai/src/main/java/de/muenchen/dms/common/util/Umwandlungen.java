/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.util;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700BusinessObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIObjectType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIUserFormsType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIAttachmentType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIMetadataType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.LHMBAI151700GIUserFormsType;
import de.muenchen.dms.common.model.AttachmentType;
import de.muenchen.dms.common.model.BusinessObjectReference;
import de.muenchen.dms.common.model.MetadataReferenz;
import de.muenchen.dms.common.model.Objektreferenz;
import de.muenchen.dms.common.model.UserFormsReferenz;
import java.time.OffsetDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * Diese Klasse bietet Methoden zur Umwandlung von Objekten an. Diese Umwandlungen sind innerhalb
 * der gesamten Anwendung verfügbar.
 */
public class Umwandlungen {

  private Umwandlungen() {}

  public static OffsetDateTime wandleZuOffsetDateTime(final XMLGregorianCalendar calendar) {
    if (calendar == null) {
      return null;
    }
    final ZonedDateTime zonedDateTime =
        calendar.toGregorianCalendar().toZonedDateTime(); /*  w ww .j a  v  a  2  s  .com*/
    return zonedDateTime.toOffsetDateTime();
  }

  public static List<Objektreferenz> wandleGiObjectTypeZuObjektreferenzen(
      ArrayOfLHMBAI151700GIObjectType soapArray) {
    List<Objektreferenz> objektreferenzen = new ArrayList<>();

    if (soapArray != null && soapArray.getLHMBAI151700GIObjectType() != null) {
      for (var objectType : soapArray.getLHMBAI151700GIObjectType()) {
        objektreferenzen.add(
            Objektreferenz.builder()
                .objname(objectType.getLHMBAI151700Objname())
                .objaddress(objectType.getLHMBAI151700Objaddress())
                .build());
      }
    }
    return objektreferenzen;
  }

  public static List<MetadataReferenz> wandleArrayGIMetadataTypeZuMetadataReferenz(
      ArrayOfLHMBAI151700GIMetadataType soapMetadataType) {
    List<MetadataReferenz> metadataReferenz = new ArrayList<>();

    if (soapMetadataType != null && soapMetadataType.getLHMBAI151700GIMetadataType() != null) {

      for (var objectType : soapMetadataType.getLHMBAI151700GIMetadataType()) {
        metadataReferenz.add(
            MetadataReferenz.builder()
                .objaddress(objectType.getLHMBAI151700Objaddress())
                .filename(objectType.getLHMBAI151700Filename())
                .fileextension(objectType.getLHMBAI151700Fileextension())
                .objclass(objectType.getLHMBAI151700Objclass())
                .contsize(objectType.getLHMBAI151700Contsize())
                .objcreatedby(objectType.getLHMBAI151700Objcreatedby())
                .objcreatedat(objectType.getLHMBAI151700Objcreatedat())
                .objchangedby(objectType.getLHMBAI151700Objchangedby())
                .objmodifiedat(objectType.getLHMBAI151700Objmodifiedat())
                .build());
      }
    }
    return metadataReferenz;
  }

  public static MetadataReferenz wandleGiMetadataTypeZuMetadataReferenzen(
      LHMBAI151700GIMetadataType soapMetaDataObject) {
    return MetadataReferenz.builder()
        .objaddress(soapMetaDataObject.getLHMBAI151700Objaddress())
        .filename(soapMetaDataObject.getLHMBAI151700Filename())
        .fileextension(soapMetaDataObject.getLHMBAI151700Fileextension())
        .objclass(soapMetaDataObject.getLHMBAI151700Objclass())
        .contsize(soapMetaDataObject.getLHMBAI151700Contsize())
        .objcreatedby(soapMetaDataObject.getLHMBAI151700Objcreatedby())
        .objcreatedat(soapMetaDataObject.getLHMBAI151700Objcreatedat())
        .objchangedby(soapMetaDataObject.getLHMBAI151700Objchangedby())
        .objmodifiedat(soapMetaDataObject.getLHMBAI151700Objmodifiedat())
        .build();
  }

  public static AttachmentType wandleGIAttachementTypeZuAttachementReferenzen(
      LHMBAI151700GIAttachmentType soapAttachementType) {
    if (soapAttachementType != null) {
      byte[] base64Bytes = soapAttachementType.getLHMBAI151700Filecontent();
      String base64ToString = Base64.getEncoder().encodeToString(base64Bytes);

      return AttachmentType.builder()
          .fileName(soapAttachementType.getLHMBAI151700Filename())
          .fileExtention(soapAttachementType.getLHMBAI151700Fileextension())
          .fileContent(base64ToString)
          .fileContsize(soapAttachementType.getLHMBAI151700Contsize())
          .filesubj(soapAttachementType.getLHMBAI151700Filesubj())
          .build();
    }
    return null;
  }

  public static List<AttachmentType> wandleArrayGIAttachmentTypeZuAttachmentReferenzen(
      ArrayOfLHMBAI151700GIAttachmentType soapArray) {

    List<AttachmentType> attachmentReferenz = new ArrayList<>();

    if (soapArray != null && soapArray.getLHMBAI151700GIAttachmentType() != null) {

      for (var objectType : soapArray.getLHMBAI151700GIAttachmentType()) {

        byte[] base64Bytes = objectType.getLHMBAI151700Filecontent();
        String base64ToString = Base64.getEncoder().encodeToString(base64Bytes);

        attachmentReferenz.add(
            AttachmentType.builder()
                .fileName(objectType.getLHMBAI151700Filename())
                .fileExtention(objectType.getLHMBAI151700Fileextension())
                .fileContent(base64ToString)
                .fileContsize(objectType.getLHMBAI151700Contsize())
                .filesubj(objectType.getLHMBAI151700Filesubj())
                .build());
      }
    }
    return attachmentReferenz;
  }

  public static List<BusinessObjectReference> wandleBusinessObjectTypeZuBusinessObjectReference(
      ArrayOfLHMBAI151700BusinessObjectType businessObjectType) {
    List<BusinessObjectReference> objektreferenz = new ArrayList<>();

    if (businessObjectType != null
        && businessObjectType.getLHMBAI151700BusinessObjectType() != null) {
      for (var objectType : businessObjectType.getLHMBAI151700BusinessObjectType()) {
        objektreferenz.add(
            BusinessObjectReference.builder()
                .objname(objectType.getLHMBAI151700Objname())
                .objaddress(objectType.getLHMBAI151700Objaddress())
                .objclass(objectType.getLHMBAI151700Objclass())
                .objcreatedat(objectType.getLHMBAI151700Objcreatedat())
                .objcreatedby(objectType.getLHMBAI151700Objcreatedby())
                .objmodifiedat(objectType.getLHMBAI151700Objmodifiedat())
                .objchangedby(objectType.getLHMBAI151700Objchangedby())
                .objowner(objectType.getLHMBAI151700Objowner())
                .objou(objectType.getLHMBAI151700Objowngroup())
                .fileextension(objectType.getLHMBAI151700Fileextension())
                .contsize(objectType.getLHMBAI151700Contsize())
                .build());
      }
    }
    return objektreferenz;
  }

  public static ArrayOfLHMBAI151700GIUserFormsType wandleUserFormsGIZuUserFormsReferenz(
      List<UserFormsReferenz> userFormsReferenz) {
    ArrayOfLHMBAI151700GIUserFormsType userFormsType = new ArrayOfLHMBAI151700GIUserFormsType();

    if (userFormsReferenz == null) {
      return null;
    }

    List<LHMBAI151700GIUserFormsType> userFormsList =
        userFormsType.getLHMBAI151700GIUserFormsType();

    if (userFormsList == null) {
      userFormsList = new ArrayList<>();
    }

    for (UserFormsReferenz referenz : userFormsReferenz) {
      LHMBAI151700GIUserFormsType userForm = new LHMBAI151700GIUserFormsType();
      userForm.setLHMBAI151700Ufreference(referenz.getLHMBAI_15_1700_ufreference());

      List<String> prefixedValues = addPrefixToUserFormsKeys(referenz.getLHMBAI_15_1700_ufvalue());
      ArrayOfstring arrayOfValue = convertToArrayOfString(prefixedValues);
      userForm.setLHMBAI151700Ufvalue(arrayOfValue);

      userFormsList.add(userForm);
    }

    return userFormsType;
  }

  private static ArrayOfstring convertToArrayOfString(List<String> values) {
    ArrayOfstring arrayOfstring = new ArrayOfstring();
    arrayOfstring.getString().addAll(values);
    return arrayOfstring;
  }

  public static List<String> addPrefixToUserFormsKeys(List<String> values) {
    List<String> prefixKeys = new ArrayList<>();
    for (String keyName : values) {
      prefixKeys.add("X" + keyName);
    }
    return prefixKeys;
  }
}
