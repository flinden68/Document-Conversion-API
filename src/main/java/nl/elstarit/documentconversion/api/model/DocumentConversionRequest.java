package nl.elstarit.documentconversion.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.File;

public class DocumentConversionRequest {

    @JsonIgnore
    private File file = null;

    @JsonIgnore
    private String media_type = "";

    public void setFile(File file) {
        this.file = file;
    }
    public File getFile() {
        return file;
    }
    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }
    public String getMedia_type() {
        return media_type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DocumentConversionRequest that = (DocumentConversionRequest) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        return media_type != null ? media_type.equals(that.media_type) : that.media_type == null;
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (media_type != null ? media_type.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "DocumentConversionRequest{" +
                "file=" + file +
                ", media_type='" + media_type + '\'' +
                '}';
    }
}
