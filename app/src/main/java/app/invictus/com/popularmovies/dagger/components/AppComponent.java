package app.invictus.com.popularmovies.dagger.components;

import android.content.SharedPreferences;

import javax.inject.Singleton;

import app.invictus.com.popularmovies.dagger.module.AppModule;
import app.invictus.com.popularmovies.dagger.module.NetModule;
import app.invictus.com.popularmovies.dagger.module.StorageModule;
import dagger.Component;
import retrofit2.Retrofit;

/**
 * Created by invictus on 12/7/16.
 */
@Singleton
@Component(modules = {
        AppModule.class, StorageModule.class,
        NetModule.class})

public interface AppComponent {

    /**
     * Downstream components need these exposed with the return type
     * Method name does not really matter
     * **/
    Retrofit retrofit();
    SharedPreferences sharedPreferences();
}
