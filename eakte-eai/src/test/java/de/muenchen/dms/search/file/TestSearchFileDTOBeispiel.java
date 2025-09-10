package de.muenchen.dms.search.file;

public class TestSearchFileDTOBeispiel {
  public static SearchFileRequestDTO erzeugeSearchFile() {
    return SearchFileRequestDTO.builder()
        .apentry("COO.1.2300.1.847")
        .filereference("A1 01 01 01 01/00010")
        .objmlname("Akte1")
        .filesubj("Betreff der Akte")
        .build();
  }
}
