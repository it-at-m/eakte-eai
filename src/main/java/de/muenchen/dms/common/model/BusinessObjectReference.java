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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@EqualsAndHashCode
public class BusinessObjectReference {
  private String objname;
  private String objaddress;
  private String objclass;
  private XMLGregorianCalendar objcreatedat;
  private ArrayOfstring objcreatedby;
  private XMLGregorianCalendar objmodifiedat;
  private ArrayOfstring objchangedby;
  private String objowner;
  private String objou;
  private String fileextension;
  private String contsize;
}
