package de.muenchen.dms.common.model;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
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
public class AttachmentType {
  private String fileName;
  private String fileExtention;
  private String fileContent;
  private String fileContsize;
  private ArrayOfstring filesubj;
}
