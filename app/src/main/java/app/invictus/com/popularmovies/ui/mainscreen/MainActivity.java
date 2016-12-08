package app.invictus.com.popularmovies.ui.mainscreen;

import android.os.Binder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import app.invictus.com.popularmovies.App;
import app.invictus.com.popularmovies.R;
import app.invictus.com.popularmovies.adapters.MoviesRecyclerAdapter;
import app.invictus.com.popularmovies.models.Result;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements MainScreenContract.View {

    @Inject
    MainScreenPresenter presenter;
    @BindView(R.id.movie_recycler)
    RecyclerView moviesRecycler;

    Unbinder butterBinder;
    private MoviesRecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainScreenComponent.builder()
                .appComponent(((App) getApplicationContext()).getAppComponent())
                .mainScreenModule(new MainScreenModule(this))
                .build().inject(this);

        ButterKnife.bind(this);

        adapter = new MoviesRecyclerAdapter(this, new ArrayList<Result>());
        initializeRecycler();

        presenter.loadMovies();
    }

    private void initializeRecycler() {
        moviesRecycler.setLayoutManager(new LinearLayoutManager(this));
        moviesRecycler.setItemAnimator(new DefaultItemAnimator());
        moviesRecycler.setAdapter(adapter);
    }

    @Override
    public void showMovies(List<Result> movies) {
        adapter.addAll(movies);
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, "error :: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showComplete() {
        Toast.makeText(this, "completed", Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
