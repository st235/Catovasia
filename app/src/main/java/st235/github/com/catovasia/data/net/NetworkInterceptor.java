package st235.github.com.catovasia.data.net;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import st235.github.com.catovasia.BuildConfig;

public class NetworkInterceptor implements Interceptor {
    private static final String CLIENT_ID_PARAM = "client_id";

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url().newBuilder()
                .addQueryParameter(CLIENT_ID_PARAM, BuildConfig.UNSPLASH_CLIENT_ID).build();
        request = request.newBuilder().url(url).build();
        return chain.proceed(request);
    }
}
