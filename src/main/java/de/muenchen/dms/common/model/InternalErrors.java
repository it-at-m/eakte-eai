package de.muenchen.dms.common.model;

public enum InternalErrors {
  DMS_CONNECTION_ERROR(1),
  STABILITY_AT_RISK(2),
  NOT_FOUND(3);

  private final int errorCode;

  InternalErrors(int errorCode) {
    this.errorCode = errorCode;
  }

  public int getErrorCode() {
    return errorCode;
  }
}
