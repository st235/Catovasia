package st235.github.com.catovasia.data.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import st235.github.com.catovasia.models.PictureResponse;

public interface UnsplashApiClient {

    @GET("/search/photos?query=cats&per_page=30")
    Call<PictureResponse> getCatsPhotos(@Query("page") int page);
}
