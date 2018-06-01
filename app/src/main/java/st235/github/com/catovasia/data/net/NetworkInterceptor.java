package st235.github.com.catovasia.data.net;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class NetworkInterceptor implements Interceptor {
    private static final String CLIENT_ID_PARAM = "client_id";

    @NonNull
    private final String clientId;

    public NetworkInterceptor(@NonNull String clientId) {
        this.clientId = clientId;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(CLIENT_ID_PARAM, clientId).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
