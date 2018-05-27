package st235.github.com.catovasia.data.net;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import st235.github.com.catovasia.BuildConfig;

/**
 * Class that creates and holds retrofit instances. Singleton. Provide with Dagger.
 */
public class RetrofitHolder {
    private Retrofit retrofitGiphy;
    private Retrofit retrofitUnsplash;
    @NonNull
    private final GsonConverterFactory gsonConverterFactory;

    public RetrofitHolder() {
        gsonConverterFactory = createGsonConverterFactory();
    }

    @NonNull
    public Retrofit buildRetrofitUnsplash() {
        OkHttpClient okHttpClient = createOkHttpClient(createNetworkInterceptor(BuildConfig.UNSPLASH_CLIENT_ID));
        return retrofitUnsplash = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.UNSPLASH_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @NonNull
    public Retrofit buildRetrofitGiphy() {
        OkHttpClient okHttpClient = createOkHttpClient(createNetworkInterceptor(BuildConfig.GIPHY_CLIENT_ID));
        return retrofitGiphy = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.GIPHY_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @NonNull
    private NetworkInterceptor createNetworkInterceptor(@NonNull String clientId) {
        return new NetworkInterceptor(clientId);
    }

    @NonNull
    private OkHttpClient createOkHttpClient(@NonNull NetworkInterceptor networkInterceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .build();
    }

    @NonNull
    private GsonConverterFactory createGsonConverterFactory() {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        return GsonConverterFactory.create(gson);
    }
}
