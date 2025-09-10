package de.muenchen.dms.startworkflow;

public class StartWorkflowDTOBeispiel {
  public static StartWorkflowDTO erzeugeStartWorkflow() {
    return StartWorkflowDTO.builder().workflowpattern("COO.1.1001.1.6485").build();
  }
}
