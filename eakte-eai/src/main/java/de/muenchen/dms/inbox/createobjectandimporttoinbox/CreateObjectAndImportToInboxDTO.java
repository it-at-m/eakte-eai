package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateObjectAndImportToInboxDTO {

  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Objekt-ID (COO-Adresse) des Postkorb",
      example = "COO.1.2302.1.8")
  private String objaddress;

  @Schema(description = "Betreff für die Datei")
  private List<List<String>> filesubj;

  public static CreateObjectAndImportToInboxDTO sample() {
    return CreateObjectAndImportToInboxDTO.builder().objaddress("COO.1.2302.1.8").build();
  }
}
