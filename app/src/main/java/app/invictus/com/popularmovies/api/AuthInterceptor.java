package app.invictus.com.popularmovies.api;

import java.io.IOException;

import app.invictus.com.popularmovies.BuildConfig;
import app.invictus.com.popularmovies.utils.AppConstants;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by invictus on 12/7/16.
 */

public class AuthInterceptor implements Interceptor {

    public AuthInterceptor() {
    }

    /**
     * This method will add the api key for every request made
     **/
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        HttpUrl url = request.url()
                .newBuilder()
                .addQueryParameter(AppConstants.API_KEY_ENTRY, BuildConfig.ACCOUNT_API_KEY)
                .build();

        request = request.newBuilder().url(url).build();

        return chain.proceed(request);
    }
}
