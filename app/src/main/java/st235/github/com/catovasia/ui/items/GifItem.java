package st235.github.com.catovasia.ui.items;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;

import st235.github.com.catovasia.R;

/**
 * ViewType for gifs
 */
public class GifItem implements Item {
    @LayoutRes
    public static final int VIEW_TYPE = R.layout.gif_item;

    @NonNull
    private String url;
    @NonNull
    private String embedUrl;
    @NonNull
    private String preview;

    public GifItem(@NonNull String embedUrl, @NonNull String url, @NonNull String preview) {
        this.embedUrl = embedUrl;
        this.url = url;
        this.preview = preview;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }

    @NonNull
    public String getEmbedUrl() {
        return embedUrl;
    }

    @NonNull
    public String getUrl() {
        return url;
    }

    public void setEmbedUrl(@NonNull String embedUrl) {
        this.embedUrl = embedUrl;
    }

    public void setUrl(@NonNull String url) {
        this.url = url;
    }

    @NonNull
    public String getPreview() {
        return preview;
    }

    public void setPreview(@NonNull String preview) {
        this.preview = preview;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GifItem item = (GifItem) o;

        if (!getUrl().equals(item.getUrl())) return false;
        if (!getEmbedUrl().equals(item.getEmbedUrl())) return false;
        return getPreview().equals(item.getPreview());
    }

    @Override
    public int hashCode() {
        int result = getUrl().hashCode();
        result = 31 * result + getEmbedUrl().hashCode();
        result = 31 * result + getPreview().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GifItem{" +
                "url='" + url + '\'' +
                ", embedUrl='" + embedUrl + '\'' +
                ", preview='" + preview + '\'' +
                '}';
    }
}
