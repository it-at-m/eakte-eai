package de.muenchen.dms.search.objname;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchObjNameGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchObjNameProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) {
    SearchObjNameAnfrageDTO dto = getMessageBody(exchange);

    final SearchObjNameGI parameters =
        searchObjNameGI(
            dto,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, SearchObjNameGI.class);
  }

  private SearchObjNameAnfrageDTO getMessageBody(Exchange exchange) {
    return exchange.getIn().getBody(SearchObjNameAnfrageDTO.class);
  }

  private SearchObjNameGI searchObjNameGI(
      final SearchObjNameAnfrageDTO anfrage,
      final String nutzer,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    final SearchObjNameGI searchObjNameGI = objectFactory.createSearchObjNameGI();
    searchObjNameGI.setUserlogin(nutzer);
    searchObjNameGI.setBusinessapp(anwendung);
    searchObjNameGI.setJoboe(organisationseinheit);
    searchObjNameGI.setJobposition(rolle);
    searchObjNameGI.setSearchstring(anfrage.getSearchstring());
    searchObjNameGI.setObjclass(anfrage.getObjclass());
    searchObjNameGI.setReference(anfrage.getReference());
    searchObjNameGI.setValue(anfrage.getValue());
    return searchObjNameGI;
  }
}
