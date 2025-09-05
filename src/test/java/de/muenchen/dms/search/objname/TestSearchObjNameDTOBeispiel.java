package de.muenchen.dms.search.objname;

public class TestSearchObjNameDTOBeispiel {
  public static SearchObjNameAnfrageDTO erzeugeSearchObjName() {
    return SearchObjNameAnfrageDTO.builder()
        .searchstring("Test*")
        .objclass("FSCACROBAT@1.1:PDF Object")
        .reference("data_dognumber")
        .value("458965")
        .build();
  }
}
