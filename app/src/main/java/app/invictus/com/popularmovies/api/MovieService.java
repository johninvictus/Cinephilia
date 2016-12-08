package app.invictus.com.popularmovies.api;

import java.util.List;

import app.invictus.com.popularmovies.models.PopularMovie;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by invictus on 12/7/16.
 */

public interface MovieService {

    @GET("popular?")
    Observable<PopularMovie> getPopularMovies();
}
