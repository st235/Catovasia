package st235.github.com.catovasia.models;

import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public final class PictureResponse {

    public static final PictureResponse EMPTY_RESPONSE = new PictureResponse();

    @Expose
    @SerializedName("results")
    private List<Picture> pictures = Collections.emptyList();

    @NonNull
    public List<Picture> getPictures() {
        return pictures;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PictureResponse that = (PictureResponse) o;

        return pictures != null ? pictures.equals(that.pictures) : that.pictures == null;
    }

    @Override
    public int hashCode() {
        return pictures != null ? pictures.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "PictureResponse{" +
                "pictures=" + pictures +
                '}';
    }
}
