package de.muenchen.dms.vorgang.aendern.complete.businessdata;

import de.muenchen.dms.vorgang.anlegen.complete.businessdata.CreateCompleteProcedureBusinessDataBodyParams;

public class CreateCompleteProcedureBusinessDataExample {
  public static CreateCompleteProcedureBusinessDataBodyParams
      erzeugeBeispielProcedureBusinessData() {
    return CreateCompleteProcedureBusinessDataBodyParams.builder()
        .businessterm("COO.1.2301.1.683")
        .businessvalue("78541235")
        .shortnameprocedure("2016 Anträge Firma XY")
        .shortnamedocument("2016 Anträge Firma XY")
        .build();
  }
}
