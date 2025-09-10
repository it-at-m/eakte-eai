package de.muenchen.dms.common.model;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfstring;
import javax.xml.datatype.XMLGregorianCalendar;
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
public class MetadataReferenz {
  private String objaddress;
  private String filename;
  private String fileextension;
  private String objclass;
  private String contsize;
  private ArrayOfstring objcreatedby;
  private XMLGregorianCalendar objcreatedat;
  private ArrayOfstring objchangedby;
  private XMLGregorianCalendar objmodifiedat;
}
