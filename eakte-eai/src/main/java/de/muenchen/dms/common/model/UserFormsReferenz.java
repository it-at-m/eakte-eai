package de.muenchen.dms.common.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class UserFormsReferenz {
  private String LHMBAI_15_1700_ufreference;
  private List<String> LHMBAI_15_1700_ufvalue;
}
