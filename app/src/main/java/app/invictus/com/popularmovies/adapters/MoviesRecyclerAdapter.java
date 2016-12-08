package app.invictus.com.popularmovies.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import app.invictus.com.popularmovies.R;
import app.invictus.com.popularmovies.models.Result;
import app.invictus.com.popularmovies.utils.AppConstants;
import app.invictus.com.popularmovies.utils.AppUtils;
import butterknife.BindView;
import butterknife.ButterKnife;
import me.gujun.android.taggroup.TagGroup;

/**
 * Created by invictus on 12/7/16.
 */

public class MoviesRecyclerAdapter extends RecyclerView.Adapter<MoviesRecyclerAdapter.ViewHolder> {

    private static final String LOG_TAG = MoviesRecyclerAdapter.class.getSimpleName();
    private final Context context;
    public List<Result> moviesList = new ArrayList<>();

    public MoviesRecyclerAdapter(Context context, List<Result> movies) {
        this.context = context;
        this.moviesList = movies;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.single_row_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.populate(moviesList.get(position));
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

    public Result getItem(int position) {
        return moviesList.get(position);
    }

    public void addAll(List<Result> movies) {
        moviesList.clear();
        moviesList.addAll(movies);
        notifyDataSetChanged();
    }

    public void add(Result movie) {
        /**add bellow
         * */
        moviesList.add(movie);
        notifyDataSetChanged();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView moviePoster;
        @BindView(R.id.title)
        TextView movieTitle;
        @BindView(R.id.rating)
        RatingBar movieRating;

        @BindView(R.id.genre)
        TagGroup movieGenre;

        @BindView(R.id.releaseYear)
        TextView movieLease;

        public ViewHolder(View view) {
            super(view);
            view.setClickable(true);
            ButterKnife.bind(this, view);
        }

        public void populate(Result result) {

            //build image url
//            Uri buildUri = Uri.parse(AppConstants.MOVIE_IMAGE_URL)
//                    .buildUpon()
//                    .appendPath(AppConstants.IMAGE_SIZE_780)
//                    .appendPath(result.getPosterPath())
//                    .build();


            Glide.with(context).load(AppConstants.MOVIE_IMAGE_URL + AppConstants.IMAGE_SIZE_780 + result.getPosterPath())
                    .into(moviePoster);

            movieTitle.setText(result.getTitle());
            /**
             * create rating
             * */
            float rating = (float) ((result.getVoteAverage() / 10) * 5.0f);
            movieRating.setRating(rating);
            movieLease.setText(AppUtils.prettyDate(result.getReleaseDate()));

            /**
             * Setting genre
             * */
            List<String> genreList = new ArrayList<>();
            genreList = AppUtils.getGenre(result.getGenreIds());


            if (!genreList.isEmpty()) {
                movieGenre.setTags(genreList);

            }
        }
    }
}
