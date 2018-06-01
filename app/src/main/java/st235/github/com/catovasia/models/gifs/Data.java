package st235.github.com.catovasia.models.gifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {
    @Expose
    @SerializedName("embed_url")
    private String embedUrl;

    @Expose
    @SerializedName("images")
    private GifData gifData;

    public String getEmbedUrl() {
        return embedUrl;
    }

    public GifData getGifData() {
        return gifData;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Data data = (Data) o;

        if (getEmbedUrl() != null ? !getEmbedUrl().equals(data.getEmbedUrl())
                : data.getEmbedUrl() != null) return false;
        return getGifData() != null ? getGifData().equals(data.getGifData())
                : data.getGifData() == null;
    }

    @Override
    public int hashCode() {
        int result = getEmbedUrl() != null ? getEmbedUrl().hashCode() : 0;
        result = 31 * result + (getGifData() != null ? getGifData().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Data{" +
                "embedUrl='" + embedUrl + '\'' +
                ", gifData=" + gifData +
                '}';
    }
}
