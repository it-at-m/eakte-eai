package de.muenchen.dms.akte.readapentry;

import de.muenchen.dms.common.model.Objektreferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.Arrays;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class ReadApentryAntwortDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description = "Vorgänge\n" + "Liste der Vorgänge der Akte",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static ReadApentryAntwortDTO sample() {
    return ReadApentryAntwortDTO.builder()
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
