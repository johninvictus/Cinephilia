package app.invictus.com.popularmovies.ui.mainscreen;

import app.invictus.com.popularmovies.dagger.components.AppComponent;
import app.invictus.com.popularmovies.dagger.module.AppModule;
import app.invictus.com.popularmovies.dagger.scopes.CustomScope;
import dagger.Component;

/**
 * Created by invictus on 12/7/16.
 */
@CustomScope
@Component(dependencies = AppComponent.class,
        modules = MainScreenModule.class)

public interface MainScreenComponent {
    void inject(MainActivity activity);
}
