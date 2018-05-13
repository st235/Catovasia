package st235.github.com.catovasia.models;

import android.graphics.Color;
import android.support.annotation.ColorInt;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class Picture {

    @ColorInt
    private int color = Color.TRANSPARENT;

    @Expose
    @SerializedName("color")
    private String pictureDominantColor;

    @Expose
    @SerializedName("urls")
    private UrlList urlList;

    @ColorInt
    public int getColor() {
        if (pictureDominantColor != null) {
            color = Color.parseColor(pictureDominantColor);
            pictureDominantColor = null;
        }
        return color;
    }

    public String getFullscreenPicture() {
        return urlList.getFullPicture();
    }

    public String getThumbnailPicture() {
        return urlList.getThumbPicture();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Picture picture = (Picture) o;

        if (pictureDominantColor != null ?
                !pictureDominantColor.equals(picture.pictureDominantColor)
                : picture.pictureDominantColor != null)
            return false;
        return urlList != null ? urlList.equals(picture.urlList) : picture.urlList == null;
    }

    @Override
    public int hashCode() {
        int result = pictureDominantColor != null ? pictureDominantColor.hashCode() : 0;
        result = 31 * result + (urlList != null ? urlList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Picture{" +
                "pictureDominantColor='" + pictureDominantColor + '\'' +
                ", urlList=" + urlList +
                '}';
    }
}
