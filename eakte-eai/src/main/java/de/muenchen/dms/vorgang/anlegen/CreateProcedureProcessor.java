/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2023
 */
package de.muenchen.dms.vorgang.anlegen;

import com.fabasoft.schemas.websvc.lhmbai_15_1700_giwsd.CreateProcedureGI;
import com.fasterxml.jackson.core.JsonProcessingException;
import de.muenchen.dms.common.processor.AbstractDMSSoapProcessor;
import de.muenchen.dms.common.util.JacksonData;
import javax.xml.datatype.DatatypeConfigurationException;
import org.apache.camel.Exchange;
import org.springframework.stereotype.Component;

@Component
public class CreateProcedureProcessor extends AbstractDMSSoapProcessor {

  @Override
  public void process(Exchange exchange)
      throws JsonProcessingException, DatatypeConfigurationException {
    final CreateProcedureDTO anfrage = exchange.getIn().getBody(CreateProcedureDTO.class);

    // Create SOAP input.
    final CreateProcedureGI parameters =
        createCreateProcedureGI(
            anfrage,
            getNutzer(exchange),
            getOrganisationseinheit(exchange),
            getRolle(exchange),
            getAnwendung(exchange));
    setParameters(exchange, parameters, CreateProcedureGI.class);
  }

  private CreateProcedureGI createCreateProcedureGI(
      final CreateProcedureDTO anfrage,
      final String userlogin,
      final String joboe,
      final String jobposition,
      final String anwendung)
      throws DatatypeConfigurationException {
    final CreateProcedureGI createProcedureGI = objectFactory.createCreateProcedureGI();
    createProcedureGI.setUserlogin(userlogin);
    createProcedureGI.setBusinessapp(anwendung);
    createProcedureGI.setReferrednumber(anfrage.getReferrednumber());
    createProcedureGI.setShortname(anfrage.getShortname());
    createProcedureGI.setFilesubj(anfrage.getFilesubj());
    createProcedureGI.setProcremark(anfrage.getProcremark());
    createProcedureGI.setJoboe(joboe);
    createProcedureGI.setJobposition(jobposition);
    createProcedureGI.setFiletype(anfrage.getFiletype());
    createProcedureGI.setObjterms(anfrage.getObjterms());
    createProcedureGI.setAccdef(anfrage.getAccdef());
    createProcedureGI.setFileruntimefrom(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimefrom()));
    createProcedureGI.setFileruntimetill(
        JacksonData.toXMLGregorianCalendar(anfrage.getFileruntimetill()));
    return createProcedureGI;
  }
}
