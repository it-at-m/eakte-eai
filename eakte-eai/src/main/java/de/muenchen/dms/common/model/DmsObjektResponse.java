package de.muenchen.dms.common.model;

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
public class DmsObjektResponse {
  private String objid;
  private String objname;

  public static DmsObjektResponse sample(final String name) {
    return DmsObjektResponse.builder().objid("COO.1.2301.1.683").objname(name + "-5").build();
  }

  public static DmsObjektResponse sampleVorgang(final String name) {
    return DmsObjektResponse.builder()
        .objid("COO.1.2301.1.683")
        .objname(name + "(0010 A20 011-4-0006)")
        .build();
  }
}
