package app.invictus.com.popularmovies.ui.mainscreen;

import app.invictus.com.popularmovies.dagger.scopes.CustomScope;
import dagger.Module;
import dagger.Provides;

/**
 * Created by invictus on 12/7/16.
 */

@Module
public class MainScreenModule {
    private final MainScreenContract.View mView;


    public MainScreenModule(MainScreenContract.View view) {
        this.mView = view;
    }

    @Provides
    @CustomScope
    MainScreenContract.View providesMainView(){
        return mView;
    }

}
