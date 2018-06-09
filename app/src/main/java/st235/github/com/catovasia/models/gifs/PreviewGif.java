package st235.github.com.catovasia.models.gifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PreviewGif {
    @Expose
    @SerializedName("url")
    private String url;

    public String getUrl() {
        return url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PreviewGif that = (PreviewGif) o;

        return getUrl() != null ? getUrl().equals(that.getUrl()) : that.getUrl() == null;
    }

    @Override
    public int hashCode() {
        return getUrl() != null ? getUrl().hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PreviewGif{" +
                "url='" + url + '\'' +
                '}';
    }
}
