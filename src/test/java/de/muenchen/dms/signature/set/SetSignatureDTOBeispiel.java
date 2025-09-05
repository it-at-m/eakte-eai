package de.muenchen.dms.signature.set;

public class SetSignatureDTOBeispiel {
  public static SetSignatureRequestDTO erzeugeSetSignature() {
    return SetSignatureRequestDTO.builder()
        .signaturetype("COO.1.1001.1.6485")
        .remark("String")
        .build();
  }
}
