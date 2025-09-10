package de.muenchen.dms.objekt.updateaccessdefinition;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.UpdateAccessDefinitionObjectGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UpdateAccessDefinitionObjectProcessorTest {
  private UpdateAccessDefinitionObjectProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new UpdateAccessDefinitionObjectProcessor();
  }

  @Test
  void testProcessor1() throws Exception {
    UpdateAccessDefinitionObjectAnfrageDTO dto = createSampleDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testProcessor2() throws Exception {
    UpdateAccessDefinitionObjectAnfrageDTO dto = new UpdateAccessDefinitionObjectAnfrageDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, exchange.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(UpdateAccessDefinitionObjectAnfrageDTO dto)
      throws Exception {
    CamelContext ctx = new DefaultCamelContext();

    Exchange ex = TestExchanges.erzeuge(ctx);
    TestExchanges.setzeHeader(ex, UpdateAccessDefinitionObjectGI.class);
    ex.getMessage().setBody(dto);

    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(
      UpdateAccessDefinitionObjectAnfrageDTO dto, Object message) {
    if (message instanceof UpdateAccessDefinitionObjectGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getObjaccdef(), equalTo(dto.getObjaccdef()));
      assertThat(gi.getAuthinheritance(), equalTo(dto.getAuthinheritance()));
    } else {
      Assertions.fail();
    }
  }

  private UpdateAccessDefinitionObjectAnfrageDTO createSampleDTO() {
    return UpdateAccessDefinitionObjectAnfrageDTO.builder()
        .objaccdef("Zugriffsdefinition für Schriftgutobjekte des Eigentümers")
        .authinheritance(2)
        .build();
  }
}
