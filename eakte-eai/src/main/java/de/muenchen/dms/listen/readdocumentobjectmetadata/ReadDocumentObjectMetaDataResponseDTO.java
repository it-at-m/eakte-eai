package de.muenchen.dms.listen.readdocumentobjectmetadata;

import de.muenchen.dms.common.model.BusinessObjectReference;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReadDocumentObjectMetaDataResponseDTO {
  @Schema(
      description = "Liste der Dokumente mit Metadaten eines Vorgangs auslesen",
      example = "BusinessObjectType")
  private List<BusinessObjectReference> businessobjecttype;
}
