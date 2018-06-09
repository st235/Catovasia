package st235.github.com.catovasia.data.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import st235.github.com.catovasia.models.gifs.GifResponse;

public interface GiphyApiClient {

    @GET("v1/gifs/search?q=funny+cats&limit=5")
    Call<GifResponse> getCatsGifs(@Query("offset") int offset);
}
