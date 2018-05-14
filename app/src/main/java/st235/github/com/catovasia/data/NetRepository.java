package st235.github.com.catovasia.data;

import android.support.annotation.MainThread;
import android.support.annotation.NonNull;

import javax.inject.Inject;
import javax.inject.Singleton;

import retrofit2.Callback;
import st235.github.com.catovasia.data.net.UnsplashApiClient;
import st235.github.com.catovasia.models.PictureResponse;

@Singleton
public class NetRepository {
    private static final String TAG = "[Catovasia] NetRep.";

    @NonNull
    private final UnsplashApiClient unsplashApiClient;

    @Inject
    NetRepository(@NonNull UnsplashApiClient unsplashApiClient) {
        this.unsplashApiClient = unsplashApiClient;
    }

    @MainThread
    public void getCatsPhotos(int page,
                              @NonNull Callback<PictureResponse> callback) {
        unsplashApiClient.getCatsPhotos(page).enqueue(callback);
    }
}
