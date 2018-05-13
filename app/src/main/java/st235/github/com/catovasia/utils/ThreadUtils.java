package st235.github.com.catovasia.utils;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.AnyThread;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;

public final class ThreadUtils {

    public interface Callback<T> {
        @MainThread
        void onReady(@NonNull T t);
        @MainThread
        void onError(@NonNull Exception e);
    }

    @NonNull
    private final Handler mainThreadHandler;

    @NonNull
    private final Executor workerThreadExecutor;

    public ThreadUtils(@NonNull Executor workerThreadExecutor) {
        mainThreadHandler = new Handler(Looper.getMainLooper());
        this.workerThreadExecutor = workerThreadExecutor;
    }

    @MainThread
    public <T> MainToWorkerMarshaller<T> createMainToWorkerMarshaller(@NonNull Callable<T> callable,
                                                                      @NonNull Callback<T> callback) {
        return new MainToWorkerMarshaller<>(callable, callback, this);
    }

    @AnyThread
    public void postRunnableOnMainThread(@NonNull final Runnable runnable) {
        mainThreadHandler.post(runnable);
    }

    @AnyThread
    public void postRunnableOnWorkerThread(@NonNull final Runnable runnable) {
        workerThreadExecutor.execute(runnable);
    }

    public static class MainToWorkerMarshaller<T> {
        @NonNull
        private final Callable<T> callable;

        @NonNull
        private final Callback<T> callback;

        @NonNull
        private final ThreadUtils threadUtils;

        @MainThread
        private MainToWorkerMarshaller(@NonNull Callable<T> callable,
                                       @NonNull Callback<T> callback,
                                       @NonNull ThreadUtils threadUtils) {
            this.callable = callable;
            this.callback = callback;
            this.threadUtils = threadUtils;
        }

        @MainThread
        public void start() {
            threadUtils.postRunnableOnWorkerThread(() -> {
                try {
                    T t = callable.call();
                    postResultOnMain(t);
                } catch (Exception e) {
                    postExceptionOnMain(e);
                }
            });
        }

        @WorkerThread
        private void postResultOnMain(T t) {
            threadUtils.postRunnableOnMainThread(() -> {
                callback.onReady(t);
            });
        }

        @WorkerThread
        private void postExceptionOnMain(Exception e) {
            threadUtils.postRunnableOnMainThread(() -> {
                callback.onError(e);
            });
        }
    }
}
