package app.invictus.com.popularmovies.ui.mainscreen;

import javax.inject.Inject;

import app.invictus.com.popularmovies.api.MovieService;
import app.invictus.com.popularmovies.models.PopularMovie;
import retrofit2.Retrofit;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by invictus on 12/7/16.
 */

public class MainScreenPresenter implements MainScreenContract.Presenter {

    Retrofit retrofit;
    MainScreenContract.View view;

    @Inject
    public MainScreenPresenter(Retrofit retrofit, MainScreenContract.View view) {
        this.retrofit = retrofit;
        this.view = view;
    }

    @Override
    public void loadMovies() {
        retrofit.create(MovieService.class)
                .getPopularMovies().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())

                .subscribe(new Observer<PopularMovie>() {
                    @Override
                    public void onCompleted() {
                        view.showComplete();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError(e.getMessage());
                    }

                    @Override
                    public void onNext(PopularMovie popularMovie) {
                        view.showMovies(popularMovie.getResults());
                    }
                });
    }
}
