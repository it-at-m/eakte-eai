/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.common.route;

/** Diese Klasse enthält Konstanten, die in anderen Klassen des Projekts benutzt werden können. */
public final class RouteConstants {

  private RouteConstants() {}

  public static final String BASIC_AUTH_SECURITY_DEFINITION = "basicAuthSecurityDefintion";

  /***************************************************************************************************
   *HEADER Params
   **************************************************************************************************/
  public static final String HEADER_PARAM_USERLOGIN = "userlogin";

  public static final String HEADER_PARAM_BUSINESSAPP = "x-anwendung";

  public static final String HEADER_PARAM_JOBOE = "joboe";

  public static final String HEADER_PARAM_JOBPOSITION = "jobposition";

  /***************************************************************************************************
   *PATH Params
   **************************************************************************************************/

  public static final String PATH_PARAM_OBJADDRESS = "objaddress";

  public static final String LOG_MSG_PROCESS_ROUTE = "Processing ${routeId}";

  public static final String PRODUCER_URL =
      "cxf:bean:producer?address=${exchangeProperty.address}&loggingFeatureEnabled=true&username=${exchangeProperty.username}&password=${exchangeProperty.authInPass}";
  public static final String DIRECT_PREFIX = "direct:";

  /***************************************************************************************************
   * CreateFile
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_FILE = "CreateFile";

  public static final String DIRECT_CREATE_FILE = DIRECT_PREFIX + ROUTE_ID_CREATE_FILE;

  /***************************************************************************************************
   * ReadFile
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_FILE = "ReadFile";

  public static final String DIRECT_READ_FILE = DIRECT_PREFIX + ROUTE_ID_READ_FILE;

  /***************************************************************************************************
   * ReadSubjectAreaFile
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_SUBJECT_AREA_FILE = "ReadSubjectAreaFile";

  public static final String DIRECT_READ_SUBJECT_AREA_FILE =
      DIRECT_PREFIX + ROUTE_ID_READ_SUBJECT_AREA_FILE;

  /***************************************************************************************************
   * UpdateFile
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_FILE = "UpdateFile";

  public static final String DIRECT_UPDATE_FILE = DIRECT_PREFIX + ROUTE_ID_UPDATE_FILE;

  /***************************************************************************************************
   * ReadApentry
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_APENTRY = "ReadApentry";

  public static final String DIRECT_READ_APENTRY = DIRECT_PREFIX + ROUTE_ID_READ_APENTRY;

  /***************************************************************************************************
   * CreateCompleteFile
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_COMPLETE_FILE = "CreateCompleteFile";

  public static final String ROUTE_ID_CREATE_COMPLETE_FILE_VERARBEITUNG =
      "CreateCompleteFileVerarbeitung";

  public static final String DIRECT_CREATE_COMPLETE_FILE =
      DIRECT_PREFIX + ROUTE_ID_CREATE_COMPLETE_FILE;

  /***************************************************************************************************
   * CreateSubjectAreaUnit
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_SUBJECT_AREA_UNIT = "CreateSubjectAreaUnit";

  public static final String DIRECT_CREATE_SUBJECT_AREA_UNIT =
      DIRECT_PREFIX + ROUTE_ID_CREATE_SUBJECT_AREA_UNIT;

  /***************************************************************************************************
   * UpdateSubjectArea
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_SUBJECT_AREA = "UpdateSubjectArea";

  public static final String DIRECT_UPDATE_SUBJECT_AREA =
      DIRECT_PREFIX + ROUTE_ID_UPDATE_SUBJECT_AREA;

  /***************************************************************************************************
   * RevokeDeposit
   **************************************************************************************************/

  public static final String ROUTE_ID_REVOKE_DEPOSIT = "RevokeDeposit";

  public static final String DIRECT_REVOKE_DEPOSIT = DIRECT_PREFIX + ROUTE_ID_REVOKE_DEPOSIT;

  /***************************************************************************************************
   * AddAddressee
   **************************************************************************************************/

  public static final String ROUTE_ID_ADD_ADDRESSEE = "AddAddressee";

  public static final String DIRECT_ADD_ADDRESSEE = DIRECT_PREFIX + ROUTE_ID_ADD_ADDRESSEE;

  /***************************************************************************************************
   * GetAddressee
   **************************************************************************************************/

  public static final String ROUTE_ID_GET_ADDRESSEE = "GetAddressee";

  public static final String DIRECT_GET_ADDRESSEE = DIRECT_PREFIX + ROUTE_ID_GET_ADDRESSEE;

  /***************************************************************************************************
   * SearchApentry
   **************************************************************************************************/

  public static final String ROUTE_ID_SEARCH_APENTRY = "SearchApentry";

  public static final String DIRECT_SEARCH_APENTRY = DIRECT_PREFIX + ROUTE_ID_SEARCH_APENTRY;

  /***************************************************************************************************
   * SearchFile
   **************************************************************************************************/

  public static final String ROUTE_ID_SEARCH_FILE = "SearchFile";

  public static final String DIRECT_SEARCH_FILE = DIRECT_PREFIX + ROUTE_ID_SEARCH_FILE;

  /***************************************************************************************************
   * SearchObjName
   **************************************************************************************************/

  public static final String ROUTE_ID_SEARCH_OBJ_NAME = "SearchObjName";

  public static final String DIRECT_SEARCH_OBJ_NAME = DIRECT_PREFIX + ROUTE_ID_SEARCH_OBJ_NAME;

  /***************************************************************************************************
   * SearchObjName
   **************************************************************************************************/

  public static final String ROUTE_ID_SEARCH_PROCEDURE = "SearchProcedure";

  public static final String DIRECT_SEARCH_PROCEDURE = DIRECT_PREFIX + ROUTE_ID_SEARCH_PROCEDURE;

  /***************************************************************************************************
   * DepositObject
   **************************************************************************************************/

  public static final String ROUTE_ID_DEPOSIT_OBJECT = "DepositObject";

  public static final String DIRECT_ROUTE_DEPOSIT_OBJECT = DIRECT_PREFIX + ROUTE_ID_DEPOSIT_OBJECT;

  /***************************************************************************************************
   * DisposalSchedule
   **************************************************************************************************/

  public static final String ROUTE_ID_DISPOSAL_SCHEDULE_DATE = "DisposalScheduleDate";

  public static final String DIRECT_DISPOSAL_SCHEDULE_DATE =
      DIRECT_PREFIX + ROUTE_ID_DISPOSAL_SCHEDULE_DATE;

  /***************************************************************************************************
   *Vorgang
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_PROCEDURE = "CreateProcedure";

  public static final String DIRECT_CREATE_PROCEDURE = DIRECT_PREFIX + ROUTE_ID_CREATE_PROCEDURE;

  /***************************************************************************************************
   * ReadProcedure
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_PROCEDURE = "ReadProcedure";

  public static final String DIRECT_READ_PROCEDURE = DIRECT_PREFIX + ROUTE_ID_READ_PROCEDURE;

  /***************************************************************************************************
   * UpdateProcedure
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_PROCEDURE = "UpdateProcedure";

  public static final String DIRECT_UPDATE_PROCEDURE = DIRECT_PREFIX + ROUTE_ID_UPDATE_PROCEDURE;

  /***************************************************************************************************
   *CreateCompleteProcedure
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_COMPLETE_PROCEDURE = "CreateCompleteProcedure";

  public static final String ROUTE_ID_CREATE_COMPLETE_PROCEDURE_VERARBEITUNG =
      "CreateCompleteProcedureVerarbeitung";

  public static final String DIRECT_CREATE_COMPLETE_PROCEDURE =
      DIRECT_PREFIX + ROUTE_ID_CREATE_COMPLETE_PROCEDURE;

  /***************************************************************************************************
   *CreateCompleteProcedureBusinessData
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA =
      "CreateCompleteProcedureBusinessData";

  public static final String ROUTE_ID_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA_VERARBEITUNG =
      "CreateCompleteProcedureBusinessDataVerarbeitung";

  public static final String DIRECT_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA =
      DIRECT_PREFIX + ROUTE_ID_CREATE_COMPLETE_PROCEDURE_BUSINESS_DATA;

  /***************************************************************************************************
   *CreateProcedureFromTemplate
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_PROCEDURE_FROM_TEMPLATE =
      "CreateProcedureFromTemplate";

  public static final String DIRECT_CREATE_PROCEDURE_FROM_TEMPLATE =
      DIRECT_PREFIX + ROUTE_ID_CREATE_PROCEDURE_FROM_TEMPLATE;

  /***************************************************************************************************
   *ReadProcedureObject
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_PROCEDURE_OBJECT = "ReadProcedureObject";

  public static final String DIRECT_READ_PROCEDURE_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_READ_PROCEDURE_OBJECT;

  /***************************************************************************************************
   * ReadSubjectAreaObject
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_SUBJECT_AREA_OBJECT = "ReadSubjectAreaObject";

  public static final String DIRECT_READ_SUBJECT_AREA_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_READ_SUBJECT_AREA_OBJECT;

  /***************************************************************************************************
   * ReadDocumentObject
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_DOCUMENT_OBJECT = "ReadDocumentObject";

  public static final String DIRECT_READ_DOCUMENT_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_READ_DOCUMENT_OBJECT;

  /***************************************************************************************************
   * ReadDocumentMetaData
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_DOCUMENT_META_DATA = "ReadDocumentMetaData";

  public static final String DIRECT_READ_DOCUMENT_META_DATA =
      DIRECT_PREFIX + ROUTE_ID_READ_DOCUMENT_META_DATA;

  /***************************************************************************************************
   *UpdateAccessDefinitionObject
   **************************************************************************************************/
  public static final String ROUTE_ID_UPDATE_ACCESS_DEFINITION_OBJECT =
      "UpdateAccessDefinitionObject";

  public static final String DIRECT_UPDATE_ACCESS_DEFINITION_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_UPDATE_ACCESS_DEFINITION_OBJECT;

  /***************************************************************************************************
   * ReAssignObject
   **************************************************************************************************/

  public static final String ROUTE_ID_REASSIGN_OBJECT = "ReAssignObject";

  public static final String DIRECT_REASSIGN_OBJECT = DIRECT_PREFIX + ROUTE_ID_REASSIGN_OBJECT;

  /***************************************************************************************************
   *ReadContentObject
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_CONTENT_OBJECT = "ReadContentObject";

  public static final String DIRECT_READ_CONTENT_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_READ_CONTENT_OBJECT;

  /***************************************************************************************************
   * CancelObject
   **************************************************************************************************/

  public static final String ROUTE_ID_CANCEL_OBJECT = "CancelObject";

  public static final String DIRECT_CANCEL_OBJECT = DIRECT_PREFIX + ROUTE_ID_CANCEL_OBJECT;

  /***************************************************************************************************
   * UpdateContentObject
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_CONTENT_OBJECT = "UpdateContentObject";

  public static final String DIRECT_UPDATE_CONTENT_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_UPDATE_CONTENT_OBJECT;

  public static final String ROUTE_ID_UPDATE_CONTENT_OBJECT_VERARBEITUNG =
      "UpdateContentObjectVerarbeitung";

  /***************************************************************************************************
   * UpdateUserFormsData
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_USER_FORMS_DATA = "UpdateUserFormsData";

  public static final String DIRECT_UPDATE_USER_FORMS_DATA =
          DIRECT_PREFIX + ROUTE_ID_UPDATE_USER_FORMS_DATA;

  public static final String ROUTE_ID_UPDATE_USER_FORMS_DATA_VERARBEITUNG =
          "UpdateUserFormsDataVerarbeitung";

  /***************************************************************************************************
   * ReadUserFormsData
  ***************************************************************************************************/

  public static final String ROUTE_ID_READ_USER_FORMS_DATA = "ReadUserFormsData";

  public static final String DIRECT_READ_USER_FORMS_DATA =
          DIRECT_PREFIX + ROUTE_ID_READ_USER_FORMS_DATA;

  /***************************************************************************************************
   * CreateContentObject
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_CONTENT_OBJECT = "CreateContentObject";

  public static final String DIRECT_CREATE_CONTENT_OBJECT =
      DIRECT_PREFIX + ROUTE_ID_CREATE_CONTENT_OBJECT;

  public static final String ROUTE_ID_CREATE_CONTENT_OBJECT_VERARBEITUNG =
      "CreateContentObjectVerarbeitung";

  /***************************************************************************************************
   * ReadContentObjectMetaData
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_CONTENT_OBJECT_META_DATA = "ReadContentObjectMetaData";

  public static final String DIRECT_READ_CONTENT_OBJECT_META_DATA =
      DIRECT_PREFIX + ROUTE_ID_READ_CONTENT_OBJECT_META_DATA;

  /***************************************************************************************************
   *CreateIncoming
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_INCOMING = "CreateIncoming";

  public static final String ROUTE_ID_CREATE_INCOMING_VERARBEITUNG = "CreateIncomingVerarbeitung";

  public static final String DIRECT_CREATE_INCOMING = DIRECT_PREFIX + ROUTE_ID_CREATE_INCOMING;

  /***************************************************************************************************
   * UpdateIncoming
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_INCOMING = "UpdateIncoming";

  public static final String DIRECT_UPDATE_INCOMING = DIRECT_PREFIX + ROUTE_ID_UPDATE_INCOMING;

  public static final String ROUTE_ID_UPDATE_INCOMING_VERARBEITUNG = "UpdateIncomingVerarbeitung";

  /***************************************************************************************************
   *CreateOutgoing
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_OUTGOING = "CreateOutgoing";

  public static final String ROUTE_ID_CREATE_OUTGOING_VERARBEITUNG = "CreateOutgoingVerarbeitung";

  public static final String DIRECT_CREATE_OUTGOING = DIRECT_PREFIX + ROUTE_ID_CREATE_OUTGOING;

  /***************************************************************************************************
   *ReadOutgoing
   **************************************************************************************************/

  public static final String ROUTE_ID_READ_OUTGOING = "ReadOutgoing";

  public static final String DIRECT_READ_OUTGOING = DIRECT_PREFIX + ROUTE_ID_READ_OUTGOING;

  /***************************************************************************************************
   *UpdateOutgoing
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_OUTGOING = "UpdateOutgoing";

  public static final String DIRECT_UPDATE_OUTGOING = DIRECT_PREFIX + ROUTE_ID_UPDATE_OUTGOING;

  public static final String ROUTE_ID_UPDATE_OUTGOING_VERARBEITUNG = "UpdateOutgoingVerarbeitung";

  /***************************************************************************************************
   *CreateInternal
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_INTERNAL = "CreateInternal";

  public static final String ROUTE_ID_CREATE_INTERNAL_VERARBEITUNG = "CreateInternalVerarbeitung";

  public static final String DIRECT_CREATE_INTERNAL = DIRECT_PREFIX + ROUTE_ID_CREATE_INTERNAL;

  /***************************************************************************************************
   *UpdateBusinessDataValue
   **************************************************************************************************/

  public static final String ROUTE_ID_UPDATE_BUSINESS_DATA_VALUE = "UpdateBusinessDataValue";

  public static final String DIRECT_UPDATE_BUSINESS_DATA_VALUE =
      DIRECT_PREFIX + ROUTE_ID_UPDATE_BUSINESS_DATA_VALUE;

  /***************************************************************************************************
   *SetSignature
   **************************************************************************************************/

  public static final String ROUTE_ID_SET_SIGNATURE = "SetSignature";

  public static final String DIRECT_SET_SIGNATURE = DIRECT_PREFIX + ROUTE_ID_SET_SIGNATURE;

  /***************************************************************************************************
   *CreateObjectAndImportToInbox
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_INCOMING_FROM_INBOX = "CreateIncomingFromInbox";

  public static final String DIRECT_CREATE_INCOMING_FROM_INBOX =
      DIRECT_PREFIX + ROUTE_ID_CREATE_INCOMING_FROM_INBOX;

  /***************************************************************************************************
   *CreateObjectAndImportToInbox
   **************************************************************************************************/

  public static final String ROUTE_ID_CREATE_OBJECT_AND_IMPORT_TO_INBOX =
      "CreateObjectAndImportToInbox";

  public static final String DIRECT_CREATE_OBJECT_AND_IMPORT_TO_INBOX =
      DIRECT_PREFIX + ROUTE_ID_CREATE_OBJECT_AND_IMPORT_TO_INBOX;

  public static final String ROUTE_ID_CREATE_OBJECT_AND_IMPORT_TO_INBOX_VERARBEITUNG =
      "CreateObjectAndImportToInboxVerarbeitung";

  /***************************************************************************************************
   *StartWorkflow
   **************************************************************************************************/

  public static final String ROUTE_ID_START_WORKFLOW = "StartWorkflow";

  public static final String DIRECT_START_WORKFLOW = DIRECT_PREFIX + ROUTE_ID_START_WORKFLOW;

  /***************************************************************************************************
   *General
   **************************************************************************************************/

  public static final String HANDLE_GENERAL_EXCEPTION_ENDPOINT = "direct:handleGeneralException";

  public static final String HANDLE_ENDPOINT_NOT_FOUND_EXCEPTION_ENDPOINT =
      "direct:handleEndpointNotFoundException";

  public static final String HANDLE_BAD_CREDENTIALS_EXCEPTION_ENDPOINT =
      "direct:handleBadCredentialsException";

  public static final String LOG_EXCEPTION_ENDPOINT = "direct:logException";

  public static final String EXCEPTION_OCCURRED_ID = "EXCEPTION_OCCURRED";

  public static final String ENDPOINT_NOT_FOUND_EXCEPTION_ID = "ENDPOINT_NOT_FOUND_EXCEPTION_ID";

  public static final String ENDPOINT_BAD_CREDENTIALS_ID = "ENDPOINT_BAD_CREDENTIALS_ID";

  /***************************************************************************************************
   *Payload
   **************************************************************************************************/

  public static final String ROUTE_ID_PAYLOAD_LOGGING = "PayloadLogger";

  public static final String DIRECT_PAYLOAD_LOGGING_ENDPOINT =
      DIRECT_PREFIX + ROUTE_ID_PAYLOAD_LOGGING;

  public static final String REQ_IN = "req_in";

  public static final String RESP_IN = "resp_in";
}
