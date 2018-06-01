package st235.github.com.catovasia.models.gifs;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Collections;
import java.util.List;

public class GifResponse {
    @Expose
    @SerializedName("data")
    private List<Data> data = Collections.emptyList();

    public List<Data> getData() {
        return data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GifResponse that = (GifResponse) o;

        return getData().equals(that.getData());
    }

    @Override
    public int hashCode() {
        return getData().hashCode();
    }

    @Override
    public String toString() {
        return "GifResponse{" +
                "data=" + data +
                '}';
    }
}
