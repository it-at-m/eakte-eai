package de.muenchen.dms.search.apentry;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchApentryGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchApentryProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {

    final SearchApentryDTO anfrage = exchange.getIn().getBody(SearchApentryDTO.class);

    final SearchApentryGI parameters =
        createSearchApentryGI(
            anfrage,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, SearchApentryGI.class);
  }

  private SearchApentryGI createSearchApentryGI(
      final SearchApentryDTO anfrage,
      final String user,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final SearchApentryGI searchApentryGI = objectFactory.createSearchApentryGI();
    searchApentryGI.setUserlogin(user);
    searchApentryGI.setBusinessapp(anwendung);
    searchApentryGI.setJobposition(rolle);
    searchApentryGI.setJoboe(organisationseinheit);
    searchApentryGI.setBasenr(anfrage.getBasenr());
    searchApentryGI.setSubjareaspecreference(anfrage.getSubjareaspecreference());
    return searchApentryGI;
  }
}
