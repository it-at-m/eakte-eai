package de.muenchen.dms.listen.readprocedureobjects;

import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReadProcedureObjectsAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Vorgänge\n" + "Liste der Vorgänge der Akte",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static ReadProcedureObjectsAntwortDTO sample() {
    return ReadProcedureObjectsAntwortDTO.builder()
        .giobjecttype(Collections.singletonList(Objektreferenz.builder().build()))
        .build();
  }
}
