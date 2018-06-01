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

    public GifItem(@NonNull String embedUrl, @NonNull String url) {
        this.embedUrl = embedUrl;
        this.url = url;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GifItem gifItem = (GifItem) o;

        if (!getEmbedUrl().equals(gifItem.getEmbedUrl())) return false;
        return getUrl().equals(gifItem.getUrl());
    }

    @Override
    public int hashCode() {
        int result = getEmbedUrl().hashCode();
        result = 31 * result + getUrl().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "GifItem{" +
                "embedUrl='" + embedUrl + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
