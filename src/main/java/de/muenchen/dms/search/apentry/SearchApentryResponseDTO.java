package de.muenchen.dms.search.apentry;

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

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class SearchApentryResponseDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.NOT_REQUIRED,
      description =
          """
                    Auflistung aller Objekte, auf die der Suchstring zutrifft.
                    Rückgabe der Eigenschaften, die im Datentyp GIObjectType definiert sind.""",
      example = "[{\"objname\" : \"Beispielname\", \"objaddress\" : \"COO.2150.9151.1.1206000\"}]")
  private List<Objektreferenz> giobjecttype;

  public static SearchApentryResponseDTO sample() {

    return SearchApentryResponseDTO.builder()
        .giobjecttype(Collections.singletonList(Objektreferenz.builder().build()))
        .build();
  }
}
