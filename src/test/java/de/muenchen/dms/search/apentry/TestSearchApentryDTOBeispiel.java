package de.muenchen.dms.search.apentry;

public class TestSearchApentryDTOBeispiel {
  public static SearchApentryDTO erzeugeSearchApentry() {
    return SearchApentryDTO.builder()
        .basenr("A1 01 01 01 01")
        .subjareaspecreference("Ableitung1")
        .build();
  }
}
