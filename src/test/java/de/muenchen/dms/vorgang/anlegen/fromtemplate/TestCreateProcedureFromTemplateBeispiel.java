package de.muenchen.dms.vorgang.anlegen.fromtemplate;

public class TestCreateProcedureFromTemplateBeispiel {
  public static CreateProcedureFromTemplateRequestDTO erzeugeBeispiel() {
    return CreateProcedureFromTemplateRequestDTO.builder()
        .referrednumber("COO.1.2300.1.847")
        .template("COO.1.2300.1.73641")
        .build();
  }
}
