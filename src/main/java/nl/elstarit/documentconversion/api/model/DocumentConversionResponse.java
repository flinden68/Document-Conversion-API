package nl.elstarit.documentconversion.api.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class DocumentConversionResponse {

    private String convertedText;
    private String message;
}
