package st235.github.com.catovasia.models.gifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GifData {
    @Expose
    @SerializedName("original")
    private Original original;

    @Expose
    @SerializedName("480w_still")
    private PreviewGif previewGif;

    public Original getOriginal() {
        return original;
    }

    public PreviewGif getPreviewGif() {
        return previewGif;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GifData gifData = (GifData) o;

        if (getOriginal() != null ? !getOriginal().equals(gifData.getOriginal())
                : gifData.getOriginal() != null)
            return false;
        return getPreviewGif() != null ? getPreviewGif().equals(gifData.getPreviewGif())
                : gifData.getPreviewGif() == null;
    }

    @Override
    public int hashCode() {
        int result = getOriginal() != null ? getOriginal().hashCode() : 0;
        result = 31 * result + (getPreviewGif() != null ? getPreviewGif().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "GifData{" +
                "original=" + original +
                ", previewGif=" + previewGif +
                '}';
    }
}
