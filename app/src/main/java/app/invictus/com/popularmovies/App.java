package app.invictus.com.popularmovies;

import android.app.Application;

import app.invictus.com.popularmovies.dagger.components.AppComponent;
import app.invictus.com.popularmovies.dagger.components.DaggerAppComponent;
import app.invictus.com.popularmovies.dagger.module.AppModule;
import app.invictus.com.popularmovies.dagger.module.NetModule;
import app.invictus.com.popularmovies.dagger.module.StorageModule;
import app.invictus.com.popularmovies.utils.AppConstants;

/**
 * Created by invictus on 12/7/16.
 */

public class App extends Application {

    AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(AppConstants.BASE_URL))
                .storageModule(new StorageModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
