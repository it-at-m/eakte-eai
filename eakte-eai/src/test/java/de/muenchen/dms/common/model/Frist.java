package de.muenchen.dms.common.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Frist {
  TAG1("1 Tag"),
  WOCHE1("1 Woche"),
  MONAT1("1 Monat"),
  MONAT3("3 Monate"),
  MONAT6("6 Monate"),
  JAHR1("1 Jahr"),
  JAHR3("3 Jahre"),
  JAHR5("5 Jahre"),
  JAHR7("7 Jahre"),
  JAHR10("10 Jahre"),
  JAHR15("15 Jahre"),
  JAHR20("20 Jahre"),
  JAHR30("30 Jahre");
  private final String name;

  Frist(String name) {
    this.name = name;
  }

  @JsonValue
  public String getValue() {
    return this.name;
  }
}
