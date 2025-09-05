package de.muenchen.dms.addressee.add;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.AddAddressee;
import de.muenchen.dms.common.util.JacksonData;
import de.muenchen.dms.test.TestExchanges;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultCamelContext;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AddAddresseeProcessorTest {

  private AddAddresseeProcessor processor;

  @BeforeEach
  public void setUp() {
    processor = new AddAddresseeProcessor();
  }

  @Test
  void addAddresseeProcessorTest() throws Exception {
    AddAddresseeRequestDTO dto = AddAddresseeDTOBeispiel.erzeugeAddAddressee();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  @Test
  void addAddresseeProcessorTestOptionaleWerteNull() throws Exception {
    AddAddresseeRequestDTO dto = new AddAddresseeRequestDTO();
    Exchange ex = prozessorAusfuehren(dto);
    stelleDatentransferAufSoapObjektSicher(dto, ex.getIn().getBody());
  }

  private Exchange prozessorAusfuehren(AddAddresseeRequestDTO dto) throws Exception {
    CamelContext ctx = new DefaultCamelContext();
    Exchange ex = TestExchanges.erzeuge(ctx, dto, AddAddresseeRequestDTO.class, AddAddressee.class);
    processor.process(ex);
    return ex;
  }

  private void stelleDatentransferAufSoapObjektSicher(AddAddresseeRequestDTO dto, Object message)
      throws DatatypeConfigurationException {
    if (message instanceof AddAddressee gi) {
      assertThat(gi.getUserlogin(), equalTo(TestExchanges.getNutzer()));
      assertThat(gi.getBusinessapp(), equalTo(TestExchanges.getAnwendung()));

      assertThat(gi.getAddrdate(), equalTo(JacksonData.toXMLGregorianCalendar(dto.getAddrdate())));
      assertThat(gi.getAddrtransmedia(), equalTo(dto.getAddrtransmedia()));
      assertThat(gi.getAddrorgname(), equalTo(dto.getAddrorgname()));
      assertThat(gi.getAddrtitle(), equalTo(dto.getAddrtitle()));
      assertThat(gi.getAddrfirstname(), equalTo(dto.getAddrfirstname()));
      assertThat(gi.getAddrname(), equalTo(dto.getAddrname()));
      assertThat(gi.getAddrstreet(), equalTo(dto.getAddrstreet()));
      assertThat(gi.getAddrstreetnumber(), equalTo(dto.getAddrstreetnumber()));
      assertThat(gi.getAddrzipcode(), equalTo(dto.getAddrzipcode()));
      assertThat(gi.getAddrcity(), equalTo(dto.getAddrcity()));
      assertThat(gi.getAddrcountry(), equalTo(dto.getAddrcountry()));
      assertThat(gi.getAddremail(), equalTo(dto.getAddremail()));
      assertThat(gi.getAddradditional5(), equalTo(dto.getAddradditional5()));

      assertThat(gi.getJoboe(), equalTo(TestExchanges.getOrganisationseinheit()));
      assertThat(gi.getJobposition(), equalTo(TestExchanges.getStelle()));
    } else {
      Assertions.fail();
    }
  }
}
