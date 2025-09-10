package de.muenchen.dms.akte.readsubjectareafile;

import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString(callSuper = true)
public class ReadSubjectAreaFileAntwortDTO {
  @Builder(builderMethodName = "builderReadSubjectAreaFile")
  public ReadSubjectAreaFileAntwortDTO(List<Objektreferenz> giobjecttype) {
    super();
    this.giobjecttype = giobjecttype;
  }

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Vorgänge\n" + "Liste der Vorgänge der Akte",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static ReadSubjectAreaFileAntwortDTO sample() {

    return ReadSubjectAreaFileAntwortDTO.builderReadSubjectAreaFile()
        .giobjecttype(erzeugeBeispielVorgangsreferenzen())
        .build();
  }

  private static List<Objektreferenz> erzeugeBeispielVorgangsreferenzen() {
    return Arrays.asList(
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207768")
            .objname("Akte 1 - Vorgang 1")
            .build(),
        Objektreferenz.builder()
            .objaddress("COO.2150.9151.1.1207769")
            .objname("Akte 1 - Vorgang 2")
            .build());
  }
}
