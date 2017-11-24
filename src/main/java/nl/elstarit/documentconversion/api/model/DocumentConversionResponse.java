package nl.elstarit.documentconversion.api.model;

public class DocumentConversionResponse {

    private String convertedText;
    private String message;

    public String getConvertedText() {
        return convertedText;
    }

    public void setConvertedText(String convertedText) {
        this.convertedText = convertedText;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentConversionResponse that = (DocumentConversionResponse) o;

        return convertedText != null ? convertedText.equals(that.convertedText) : that.convertedText == null;
    }

    @Override
    public int hashCode() {
        return convertedText != null ? convertedText.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "DocumentConversionResponse{" +
                "convertedText='" + convertedText + '\'' +
                "message='" + message + '\'' +
                '}';
    }
}
