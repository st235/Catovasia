package st235.github.com.catovasia.models.pictures;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public final class UrlList {
    @Expose
    @SerializedName("full")
    private String fullPicture;

    @Expose
    @SerializedName("thumb")
    private String thumbPicture;

    public String getFullPicture() {
        return fullPicture;
    }

    public String getThumbPicture() {
        return thumbPicture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UrlList urlList = (UrlList) o;

        if (fullPicture != null ? !fullPicture.equals(urlList.fullPicture) : urlList.fullPicture != null)
            return false;
        return thumbPicture != null ? thumbPicture.equals(urlList.thumbPicture) : urlList.thumbPicture == null;
    }

    @Override
    public int hashCode() {
        int result = fullPicture != null ? fullPicture.hashCode() : 0;
        result = 31 * result + (thumbPicture != null ? thumbPicture.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "UrlList{" +
                "fullPicture='" + fullPicture + '\'' +
                ", thumbPicture='" + thumbPicture + '\'' +
                '}';
    }
}
