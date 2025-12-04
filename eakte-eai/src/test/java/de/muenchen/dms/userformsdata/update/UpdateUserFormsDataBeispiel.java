package de.muenchen.dms.userformsdata.update;

import de.muenchen.dms.common.model.UserFormsReferenz;
import java.util.Arrays;
import java.util.List;

public class UpdateUserFormsDataBeispiel {

  public static UpdateUserFormsDataRequestDTO erzeugeUpdateUserFormsDataRequestDTO() {
    UserFormsReferenz userFormsReferenzItems =
        UserFormsReferenz.builder()
            .LHMBAI_15_1700_ufreference("Referenz")
            .LHMBAI_15_1700_ufvalue(Arrays.asList("Wert1", "Wert2"))
            .build();

    List<UserFormsReferenz> userFormsReferenzItemsList = Arrays.asList(userFormsReferenzItems);
    return UpdateUserFormsDataRequestDTO.builder()
        .userformsdata(userFormsReferenzItemsList)
        .build();
  }
}
