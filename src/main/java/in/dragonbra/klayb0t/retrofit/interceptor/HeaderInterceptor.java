package in.dragonbra.klayb0t.retrofit.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * @author lngtr
 * @since 2018-01-04
 */
public class HeaderInterceptor implements Interceptor {

    private final String headerName;

    private final String headerValue;

    public HeaderInterceptor(String headerName, String headerValue) {
        this.headerName = headerName;
        this.headerValue = headerValue;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        Request newRequest = request.newBuilder()
                .addHeader(headerName, headerValue)
                .build();

        return chain.proceed(newRequest);
    }
}
