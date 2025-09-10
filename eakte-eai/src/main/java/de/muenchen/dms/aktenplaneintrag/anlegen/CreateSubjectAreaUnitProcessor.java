package de.muenchen.dms.aktenplaneintrag.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateSubjectAreaUnitGI;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateSubjectAreaUnitProcessor extends AbstractDMSSoapProcessor {
  @Override
  public void process(Exchange exchange) throws Exception {
    final CreateSubjectAreaUnitAnfrageDTO anfrage =
        exchange.getIn().getBody(CreateSubjectAreaUnitAnfrageDTO.class);

    final CreateSubjectAreaUnitGI parameters =
        createcreateSubjectAreaUnitGI(
            getNutzer(exchange),
            anfrage,
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, CreateSubjectAreaUnitGI.class);
  }

  private CreateSubjectAreaUnitGI createcreateSubjectAreaUnitGI(
      final String nutzer,
      final CreateSubjectAreaUnitAnfrageDTO anfrage,
      final String organisationseinheit,
      final String rolle,
      final String anwendung) {
    CreateSubjectAreaUnitGI createSubjectAreaUnitGI = new CreateSubjectAreaUnitGI();
    createSubjectAreaUnitGI.setUserlogin(nutzer);
    createSubjectAreaUnitGI.setBusinessapp(anwendung);
    createSubjectAreaUnitGI.setObjaddress(anfrage.getObjaddress());
    createSubjectAreaUnitGI.setJoboe(organisationseinheit);
    createSubjectAreaUnitGI.setJobposition(rolle);
    createSubjectAreaUnitGI.setBasenr(anfrage.getBasenr());
    createSubjectAreaUnitGI.setSubjareaspecreference(anfrage.getSubjareaspecreference());
    createSubjectAreaUnitGI.setShortterm(anfrage.getShortterm());
    createSubjectAreaUnitGI.setSubjarchiveschedule(anfrage.getSubjarchiveschedule());
    createSubjectAreaUnitGI.setSubjschedule(anfrage.getSubjschedule());
    createSubjectAreaUnitGI.setSubjdispstate(anfrage.getSubjdispstate());
    createSubjectAreaUnitGI.setFileaccessdefinition(anfrage.getFileaccessdefinition());

    return createSubjectAreaUnitGI;
  }
}
