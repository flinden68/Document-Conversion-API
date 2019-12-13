package nl.elstarit.documentconversion.api.model;

import lombok.Data;

@Data
public class DocumentConversionRequest {

    private String mediaType;
    private String data;

}
