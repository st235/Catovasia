package st235.github.com.catovasia.di;

import android.content.Context;
import android.content.res.AssetManager;
import android.support.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import st235.github.com.catovasia.data.assets.AssetsProvider;
import st235.github.com.catovasia.data.net.GiphyApiClient;
import st235.github.com.catovasia.data.net.RetrofitHolder;
import st235.github.com.catovasia.data.net.UnsplashApiClient;

@Module
class DataModule {

    @NonNull
    private final Context context;

    @NonNull
    private final RetrofitHolder retrofitHolder;

    DataModule(@NonNull Context context) {
        this.context = context;
        retrofitHolder = new RetrofitHolder();
    }

    @Provides
    @Singleton
    UnsplashApiClient provideUnsplashClient() {
        return retrofitHolder.buildRetrofitUnsplash().create(UnsplashApiClient.class);
    }

    @Provides
    @Singleton
    GiphyApiClient provideGiphyClient() {
        return retrofitHolder.buildRetrofitGiphy().create(GiphyApiClient.class);
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
