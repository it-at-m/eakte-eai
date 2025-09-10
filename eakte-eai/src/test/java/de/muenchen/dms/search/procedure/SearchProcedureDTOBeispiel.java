package de.muenchen.dms.search.procedure;

public class SearchProcedureDTOBeispiel {
  public static SearchProcedureRequestDTO erzeugeSearchProcedure() {
    return SearchProcedureRequestDTO.builder()
        .referrednumber("COO.1.2300.1.847")
        .filereference("A1 01 01 01 01/00010/0001")
        .objmlname("Vorgang 1")
        .filesubj("Betreff des Vorgangs")
        .build();
  }
}
