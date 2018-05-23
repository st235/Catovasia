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
import st235.github.com.catovasia.data.net.UnsplashApiClient;
import st235.github.com.catovasia.models.Picture;
import st235.github.com.catovasia.models.PictureResponse;
import st235.github.com.catovasia.data.net.Result;

@Singleton
public class NetRepository {
    private static final String TAG = "[Catovasia] NetRep.";

    @NonNull
    private final UnsplashApiClient unsplashApiClient;
    private final MutableLiveData<Result<List<Picture>>> liveData = new MutableLiveData<>();

    @Inject
    NetRepository(@NonNull UnsplashApiClient unsplashApiClient) {
        this.unsplashApiClient = unsplashApiClient;
    }

    @MainThread
    public LiveData<Result<List<Picture>>> getCatsPhotos(int page) {
        unsplashApiClient.getCatsPhotos(page).enqueue(new Callback<PictureResponse>() {
            @Override
            public void onResponse(@NonNull Call<PictureResponse> call, @NonNull Response<PictureResponse> response) {
                if (response.body() != null) {
                    liveData.setValue(Result.createSuccess(response.body().getPictures()));
                }
            }

            @Override
            public void onFailure(@NonNull Call<PictureResponse> call, @NonNull Throwable t) {
                liveData.setValue(Result.createError(t.getMessage(), Collections.emptyList()));
                Log.e(TAG, t.getLocalizedMessage());
            }
        });

        return liveData;
    }
}
