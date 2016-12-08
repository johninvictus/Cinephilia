package app.invictus.com.popularmovies.dagger.module;

import android.app.Application;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.inject.Singleton;

import app.invictus.com.popularmovies.api.AuthInterceptor;
import dagger.Module;
import dagger.Provides;
import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by invictus on 12/7/16.
 */

@Module
public class NetModule {

    private String baseUrl;

    public NetModule(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    @Provides
    @Singleton
    Cache providesHttpCache(Application application) {
        /**
         * Cache size
         * **/
        int cacheSize = 10 * 1024 * 1024;
        Cache cache = new Cache(application.getCacheDir(), cacheSize);
        return cache;
    }

    @Provides
    @Singleton
    Gson providesGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES);
        return gsonBuilder.create();
    }

    @Provides
    @Singleton
    Interceptor providesAuthInterceptor() {
        return new AuthInterceptor();
    }

    @Provides
    @Singleton
    OkHttpClient providesOkHttpClient(Cache cache, Interceptor interceptor) {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        /**
         * set Cache
         * */
        builder.cache(cache);
        /**
         * set an Auth interceptor interceptor
         * //maybe later we will add loging iterceptor
         * */
        builder.addInterceptor(interceptor);
        return builder.build();
    }

    @Provides
    @Singleton
    RxJavaCallAdapterFactory providesRxjavaAdapterFactory() {
        return RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
    }

    @Provides
    @Singleton
    Retrofit providesRetrofit(Gson gson, OkHttpClient okHttpClient,
                              RxJavaCallAdapterFactory rxJavaCallAdapterFactory) {

        Retrofit.Builder retrofit = new Retrofit.Builder();
        retrofit.addConverterFactory(GsonConverterFactory.create(gson));
        retrofit.addCallAdapterFactory(rxJavaCallAdapterFactory);
        retrofit.baseUrl(baseUrl);
        retrofit.client(okHttpClient);

        return retrofit.build();
    }
}
