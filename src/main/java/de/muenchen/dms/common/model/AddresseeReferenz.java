package de.muenchen.dms.common.model;

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
@EqualsAndHashCode
@ToString
public class AddresseeReferenz {
  private XMLGregorianCalendar addrdate;
  private String addrtransmedia;
  private String addrOrgName;
  private String addrtitle;
  private String addrfirstname;
  private String addrname;
  private String addrstreet;
  private String addrstreetnumber;
  private String addrzipcode;
  private String addrcity;
  private String addrcountry;
  private String addremail;
  private String addradditional5;
}
