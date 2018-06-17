package st235.github.com.catovasia.data.net;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static st235.github.com.catovasia.data.net.Result.Status.ERROR;
import static st235.github.com.catovasia.data.net.Result.Status.SUCCESS;

public class Result<T> {

    @NonNull
    private final Status status;
    @Nullable
    private final T data;
    @Nullable
    private final String message;

    private Result(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static<T> Result<T> createSuccess(@NonNull T data){
        return new Result<>(SUCCESS, data, null);
    }

    public static <T> Result<T> createError(@NonNull String message, @Nullable T data){
        return new Result<>(ERROR, data, message);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    @Nullable
    public T getData() {
        return data;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public enum Status {
        SUCCESS,
        ERROR;
    }
}
