package de.muenchen.dms.userformsdata.update;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.ArrayOfLHMBAI151700GIUserFormsType;
import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateUserFormsDataGI;
import de.muenchen.dms.test.TestExchanges;
import java.util.List;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UpdateUserFormsDataProcessorTest {
  private UpdateUserFormsDataProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateUserFormsDataProcessor();
  }

  @Test
  @DisplayName(
      """
              Prüfe, ob ein gültiges, vollständig gefülltes Rest-Datenobjekt in ein Datenobjekt
              für einen SOAP-Call ohne Fehler umgewandelt werden kann.
              """)
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    UpdateUserFormsDataRequestDTO dto =
        UpdateUserFormsDataBeispiel.erzeugeUpdateUserFormsDataRequestDTO();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  @DisplayName(
      """
                  Prüfe, ob ein gültiges, nur mit den nötigsten Felder belegtes Rest-Datenobjekt in
                  ein SOAP-Objekt umgewandelt werden kann.
                  """)
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    UpdateUserFormsDataRequestDTO dto = new UpdateUserFormsDataRequestDTO();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(UpdateUserFormsDataRequestDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex =
        TestExchanges.erzeuge(
            ctx, dto, UpdateUserFormsDataRequestDTO.class, UpdateUserFormsDataGI.class);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateUserFormsDataRequestDTO dto, Object message) {
    if (message instanceof UpdateUserFormsDataGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));

      ArrayOfLHMBAI151700GIUserFormsType userFormsType = gi.getUserformsdata();

      if (dto.getUserformsdata() == null || dto.getUserformsdata().isEmpty()) {
        if (userFormsType == null
            || userFormsType.getLHMBAI151700GIUserFormsType() == null
            || userFormsType.getLHMBAI151700GIUserFormsType().isEmpty()) {
          return;
        } else {
          Assertions.fail(
              "SOAP enthält einträge obwohl DTO leer/null war:"
                  + userFormsType.getLHMBAI151700GIUserFormsType());
        }
      }
      assertThat(userFormsType, notNullValue());
      assertThat(
          userFormsType.getLHMBAI151700GIUserFormsType().size(),
          equalTo(dto.getUserformsdata().size()));

      var expected = dto.getUserformsdata().get(0);
      var actual = userFormsType.getLHMBAI151700GIUserFormsType().get(0);

      assertThat(
          actual.getLHMBAI151700Ufreference(), equalTo(expected.getLHMBAI_15_1700_ufreference()));

      List<String> listStringValues = actual.getLHMBAI151700Ufvalue().getString();
      assertThat(listStringValues, equalTo(expected.getLHMBAI_15_1700_ufvalue()));
    } else {
      Assertions.fail();
    }
  }
}
