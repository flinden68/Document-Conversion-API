package nl.elstarit.documentconversion.api.model;

public class DocumentConversionRequest {

    private String mediaType;
    private String data;

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentConversionRequest that = (DocumentConversionRequest) o;

        if (mediaType != null ? !mediaType.equals(that.mediaType) : that.mediaType != null) return false;
        return data != null ? data.equals(that.data) : that.data == null;
    }

    @Override
    public int hashCode() {
        int result = mediaType != null ? mediaType.hashCode() : 0;
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentConversionRequest{" +
                "mediaType='" + mediaType + '\'' +
                ", data='" + data + '\'' +
                '}';
    }
}
