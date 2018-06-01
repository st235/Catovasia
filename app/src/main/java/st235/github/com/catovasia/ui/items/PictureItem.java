package st235.github.com.catovasia.ui.items;

import android.support.annotation.NonNull;

import st235.github.com.catovasia.R;

/**
 * ViewType for pictures
 */
public class PictureItem implements Item {
    public static final int VIEW_TYPE = R.layout.picture_item;

    @NonNull
    private String catName;
    @NonNull
    private String fullPicture;
    @NonNull
    private String thumbPicture;
    @NonNull
    private String pictureDominantColor;

    public PictureItem(@NonNull String catName,
                       @NonNull String fullPicture,
                       @NonNull String thumbPicture,
                       @NonNull String pictureDominantColor) {
        this.catName = catName;
        this.fullPicture = fullPicture;
        this.thumbPicture = thumbPicture;
        this.pictureDominantColor = pictureDominantColor;
    }

    @Override
    public int getViewType() {
        return VIEW_TYPE;
    }

    @NonNull
    public String getCatName() {
        return catName;
    }

    @NonNull
    public String getPictureDominantColor() {
        return pictureDominantColor;
    }

    @NonNull
    public String getFullPicture() {
        return fullPicture;
    }

    @NonNull
    public String getThumbPicture() {
        return thumbPicture;
    }

    public void setCatName(@NonNull String catName) {
        this.catName = catName;
    }

    public void setFullPicture(@NonNull String fullPicture) {
        this.fullPicture = fullPicture;
    }

    public void setThumbPicture(@NonNull String thumbPicture) {
        this.thumbPicture = thumbPicture;
    }

    public void setPictureDominantColor(@NonNull String pictureDominantColor) {
        this.pictureDominantColor = pictureDominantColor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureItem feedItem = (PictureItem) o;

        if (getCatName() != null ? !getCatName().equals(feedItem.getCatName()) : feedItem.getCatName() != null) return false;
        if (!getFullPicture().equals(feedItem.getFullPicture())) return false;
        if (!getThumbPicture().equals(feedItem.getThumbPicture())) return false;
        return getPictureDominantColor().equals(feedItem.getPictureDominantColor());
    }

    @Override
    public int hashCode() {
        int result = getCatName() != null ? getCatName().hashCode() : 0;
        result = 31 * result + getFullPicture().hashCode();
        result = 31 * result + getThumbPicture().hashCode();
        result = 31 * result + getPictureDominantColor().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "FeedItem{" +
                "catName='" + catName + '\'' +
                ", fullPicture='" + fullPicture + '\'' +
                ", thumbPicture='" + thumbPicture + '\'' +
                ", pictureDominantColor='" + pictureDominantColor + '\'' +
                '}';
    }
}
