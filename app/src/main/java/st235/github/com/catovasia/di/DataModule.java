package st235.github.com.catovasia.di;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import st235.github.com.catovasia.BuildConfig;
import st235.github.com.catovasia.data.assets.AssetsProvider;
import st235.github.com.catovasia.data.net.NetworkInterceptor;
import st235.github.com.catovasia.data.net.UnsplashApiClient;

@Module
class DataModule {

    @NonNull
    private final Context context;

    @NonNull
    private final Retrofit retrofit;

    DataModule(@NonNull Context context) {
        this.context = context;

        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
        GsonConverterFactory gsonConverterFactory = GsonConverterFactory.create(gson);

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new NetworkInterceptor())
                .build();

        retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.UNSPLASH_URL)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    UnsplashApiClient provideUnsplashClient() {
        return retrofit.create(UnsplashApiClient.class);
    }

    @Provides
    @Singleton
    AssetManager provideAssetManager() {
        return context.getAssets();
    }

    @Provides
    @Singleton
    AssetsProvider provideAssetsProvider(AssetManager assetManager) {
        return new AssetsProvider(assetManager);
    }
}
