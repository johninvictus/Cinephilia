package app.invictus.com.popularmovies.ui.mainscreen;

import java.util.List;

import app.invictus.com.popularmovies.models.Result;

/**
 * Created by invictus on 12/7/16.
 */

public interface MainScreenContract {
    interface View {
        void showMovies(List<Result> movies);

        void showError(String message);

        void showComplete();
    }

    interface Presenter {
        void loadMovies();
    }
}
