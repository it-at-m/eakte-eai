package de.muenchen.dms.inbox.createobjectandimporttoinbox;

import de.muenchen.dms.common.model.MetadataReferenz;
import io.swagger.v3.oas.annotations.media.Schema;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CreateObjectAndImportToInboxResponseDTO {

  @Schema() private List<MetadataReferenz> listcontents;
}
