package de.muenchen.dms.inbox.createincomingfrominbox;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateIncomingFromInboxGI;
import de.muenchen.dms.test.TestExchanges;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateIncomingFromInboxProcessorTest {

  private CreateIncomingFromInboxProcessor processor;

  @BeforeEach
  void setUp() {
    processor = new CreateIncomingFromInboxProcessor();
  }

  @Test
  void testeKonvertierungInSOAPObjektAlleWerteVorhanden() throws Exception {
    CreateIncomingFromInboxRequestDTO dto = erzeugeRESTDatenobjekt();

    Exchange exchange = prozessorAusfuehren(dto);

    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  @Test
  void testeKonvertierungInSOAPObjektOptionaleWerteNull() throws Exception {
    CreateIncomingFromInboxRequestDTO dto = new CreateIncomingFromInboxRequestDTO();
    Exchange exchange = prozessorAusfuehren(dto);
    stelleDatentransferAufSOAPObjektSicher(dto, exchange.getIn().getBody());
  }

  private CreateIncomingFromInboxRequestDTO erzeugeRESTDatenobjekt() {
    return CreateIncomingFromInboxRequestDTO.builder()
        .inboxid("COO.1.2301.4.191")
        .referrednumber("COO.1.2301.1.1042432")
        .contentid("COO.1.2301.1.1042446")
        .shortname("Eingangspost vom 21.10.2016")
        .foreignnr("A2016-10-2016-Landeshauptstadt-München")
        .filesubj("Schreiben der Firma Mustermann bezüglich Beantragung Baugenehmigung")
        .documentremarks("Antrag auf Baugenehmigung")
        .incattachments("Bebauungsplan")
        .objterms("Firma XY; Anträge; Anträge 2016")
        .build();
  }

  public Exchange prozessorAusfuehren(CreateIncomingFromInboxRequestDTO dto) throws Exception {
    CamelContext camelContext = new DefaultCamelContext();
    Exchange exchange =
        TestExchanges.erzeuge(
            camelContext,
            dto,
            CreateIncomingFromInboxRequestDTO.class,
            CreateIncomingFromInboxGI.class);
    processor.process(exchange);
    return exchange;
  }

  public void stelleDatentransferAufSOAPObjektSicher(
      CreateIncomingFromInboxRequestDTO dto, Object message) {
    if (message instanceof CreateIncomingFromInboxGI gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getInboxid(), equalTo(dto.getInboxid()));
      assertThat(gi.getReferrednumber(), equalTo(dto.getReferrednumber()));
      assertThat(gi.getContentid(), equalTo(dto.getContentid()));
      assertThat(gi.getShortname(), equalTo(dto.getShortname()));
      assertThat(gi.getForeignnr(), equalTo(dto.getForeignnr()));
      assertThat(gi.getFilesubj(), equalTo(dto.getFilesubj()));
      assertThat(gi.getDocumentremarks(), equalTo(dto.getDocumentremarks()));
      assertThat(gi.getIncattachments(), equalTo(dto.getIncattachments()));
      assertThat(gi.getObjterms(), equalTo(dto.getObjterms()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));
    } else {
      Assertions.fail(message.toString());
    }
  }
}
