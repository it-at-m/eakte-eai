package de.muenchen.dms.common.route.auth;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DmsAuthorizationProcessorConfig {
  private String eaiServiceUser;
  private String eaiServicePassword;

  public DmsAuthorizationProcessorConfig() {
    this.eaiServiceUser = "defaultuser"; // NOSONAR
    this.eaiServicePassword = "defaultpassword"; // NOSONAR
  }
}
