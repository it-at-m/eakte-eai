package de.muenchen.dms.disposalschedule.aendern;

import io.swagger.v3.oas.annotations.media.Schema;
import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SetDisposalScheduleDateAnfrageDTO {
  @Schema(
      requiredMode = Schema.RequiredMode.REQUIRED,
      description = "Datum bei der vorgesehenen Aufbewahrungsfrist bei Aufbewahrung",
      example = "2016-02-25T")
  private OffsetDateTime offltscheduled;

  public static SetDisposalScheduleDateAnfrageDTO sample() {
    return SetDisposalScheduleDateAnfrageDTO.builder()
        .offltscheduled(OffsetDateTime.now().withNano(0))
        .build();
  }
}
