package st235.github.com.catovasia.di;

import android.os.AsyncTask;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import st235.github.com.catovasia.utils.ThreadUtils;

@Module
class UtilsModule {

    @Provides
    @Singleton
    ThreadUtils provideThreadUtils() {
        return new ThreadUtils(AsyncTask.THREAD_POOL_EXECUTOR);
    }
}
