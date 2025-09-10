/*
 * Copyright (c): it@M - Dienstleister für Informations- und Telekommunikationstechnik
 * der Landeshauptstadt München, 2018
 */
package de.muenchen.dms.common.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Attachment {
  private String fileName;
  private String fileExtention;

  @JsonIgnore
  @Setter(onMethod_ = @SuppressFBWarnings("EI_EXPOSE_REP"))
  @Getter(onMethod_ = @SuppressFBWarnings("EI_EXPOSE_REP"))
  private byte[] content;
}
