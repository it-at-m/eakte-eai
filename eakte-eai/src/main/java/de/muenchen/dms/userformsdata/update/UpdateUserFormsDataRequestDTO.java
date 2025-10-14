package de.muenchen.dms.userformsdata.update;

import de.muenchen.dms.common.model.UserFormsReferenz;
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
public class UpdateUserFormsDataRequestDTO {

  @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED)
  private List<UserFormsReferenz> userformsdata;
}
