package st235.github.com.catovasia.models.gifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Original {
    @Expose
    @SerializedName("url")
    private String url;

    @Expose
    @SerializedName("mp4")
    private String mp4Format;

    @Expose
    @SerializedName("webp")
    private String webpFormat;

    public String getUrl() {
        return url;
    }

    public String getMp4Format() {
        return mp4Format;
    }

    public String getWebpFormat() {
        return webpFormat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Original original = (Original) o;

        if (getUrl() != null ? !getUrl().equals(original.getUrl())
                : original.getUrl() != null)
            return false;
        if (getMp4Format() != null ? !getMp4Format().equals(original.getMp4Format())
                : original.getMp4Format() != null)
            return false;
        return getWebpFormat() != null ? getWebpFormat().equals(original.getWebpFormat())
                : original.getWebpFormat() == null;
    }

    @Override
    public int hashCode() {
        int result = getUrl() != null ? getUrl().hashCode() : 0;
        result = 31 * result + (getMp4Format() != null ? getMp4Format().hashCode() : 0);
        result = 31 * result + (getWebpFormat() != null ? getWebpFormat().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Original{" +
                "url='" + url + '\'' +
                ", mp4Format='" + mp4Format + '\'' +
                ", webpFormat='" + webpFormat + '\'' +
                '}';
    }
}
