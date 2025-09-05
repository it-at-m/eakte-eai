package de.muenchen.dms.search.file;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.SearchFileGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class SearchFileProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final SearchFileRequestDTO dto = exchange.getIn().getBody(SearchFileRequestDTO.class);

    final SearchFileGI parameters =
        searchFile(
            getNutzer(exchange),
            dto,
            getRolle(exchange),
            getOrganisationseinheit(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, SearchFileGI.class);
  }

  private SearchFileGI searchFile(
      final String userlogin,
      final SearchFileRequestDTO dto,
      final String jobposition,
      final String joboe,
      final String businessapp) {
    final SearchFileGI searchFileGI = objectFactory.createSearchFileGI();
    searchFileGI.setUserlogin(userlogin);
    searchFileGI.setApentry(dto.getApentry());
    searchFileGI.setFilereference(dto.getFilereference());
    searchFileGI.setObjmlname(dto.getObjmlname());
    searchFileGI.setFilesubj(dto.getFilesubj());
    searchFileGI.setJobposition(jobposition);
    searchFileGI.setJoboe(joboe);
    searchFileGI.setBusinessapp(businessapp);

    return searchFileGI;
  }
}
