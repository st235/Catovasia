package st235.github.com.catovasia.data;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import st235.github.com.catovasia.data.net.GiphyApiClient;
import st235.github.com.catovasia.data.net.UnsplashApiClient;
import st235.github.com.catovasia.models.gifs.Data;
import st235.github.com.catovasia.models.gifs.GifResponse;
import st235.github.com.catovasia.models.pictures.Picture;
import st235.github.com.catovasia.models.pictures.PictureResponse;
import st235.github.com.catovasia.data.net.Result;

@Singleton
public class NetRepository {
    private static final String TAG = "[Catovasia] NetRep.";

    @NonNull
    private final GiphyApiClient giphyApiClient;
    @NonNull
    private final UnsplashApiClient unsplashApiClient;
    private final MutableLiveData<Result<List<Data>>> giphyLiveData = new MutableLiveData<>();
    private final MutableLiveData<Result<List<Picture>>> pictureLiveData = new MutableLiveData<>();

    @Inject
    NetRepository(@NonNull UnsplashApiClient unsplashApiClient,
                  @NonNull GiphyApiClient giphyApiClient) {
        this.unsplashApiClient = unsplashApiClient;
        this.giphyApiClient = giphyApiClient;
    }

    @MainThread
    public LiveData<Result<List<Picture>>> getCatsPhotos(int page) {
        unsplashApiClient.getCatsPhotos(page).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(@NonNull Call<PictureResponse> call, @NonNull Response<PictureResponse> response) {
                if (response.body() != null) {
                    pictureLiveData.setValue(Result.createSuccess(response.body().getPictures()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PictureResponse> call, @NonNull Throwable t) {
                pictureLiveData.setValue(Result.createError(t.getMessage(), Collections.emptyList()));
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

        return pictureLiveData;
    }

    @MainThread
    public LiveData<Result<List<Data>>> getCatGifs(int offset){
        giphyApiClient.getCatsGifs(offset).enqueue(new Callback<GifResponse>() {
            @Override
            public void onResponse(@NonNull Call<GifResponse> call, @NonNull Response<GifResponse> response) {
                if (response.body() != null) {
                    giphyLiveData.setValue(Result.createSuccess(response.body().getData()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<GifResponse> call, @NonNull Throwable t) {
                giphyLiveData.setValue(Result.createError(t.getMessage(), Collections.emptyList()));
                Log.e(TAG, t.getLocalizedMessage());
            }
        });
        return giphyLiveData;
    }
}
