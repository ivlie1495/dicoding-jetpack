package com.example.submissionjetpack.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.submissionjetpack.R;
import com.example.submissionjetpack.config.RoomConfig;
import com.example.submissionjetpack.constant.ApiConstants;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.viewmodel.MovieViewModel;
import com.example.submissionjetpack.viewmodel.TvShowViewModel;
import com.example.submissionjetpack.viewmodel.ViewModelFactory;

public class DetailActivity extends AppCompatActivity {

    private TextView textViewTitleDetail;
    private TextView textViewReleaseDetail;
    private TextView textViewScoreDetail;
    private TextView textViewOverviewDetail;
    private ImageView imageViewDetail;

    private Movie thisMovie;
    private TvShow thisTvShow;

    private Menu menuItem;
    private boolean isFavourite = false;

    private MovieViewModel movieViewModel;
    private TvShowViewModel tvShowViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        textViewTitleDetail = findViewById(R.id.textViewTitleDetail);
        textViewReleaseDetail = findViewById(R.id.textViewReleaseDetail);
        textViewScoreDetail = findViewById(R.id.textViewScoreDetail);
        textViewOverviewDetail = findViewById(R.id.textViewOverviewDetail);
        imageViewDetail = findViewById(R.id.imageViewDetail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        getDataValue(isMovie());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favourite, menu);
        menuItem = menu;
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.onBackPressed();
                break;
            case R.id.action_favourite:
                addOrRemoveToFavourite();
                setFavourite();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @NonNull
    private static MovieViewModel obtainMovieViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @NonNull
    private static TvShowViewModel obtainTvShowViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }

    private void getDataValue(boolean isMovie) {


        if (isMovie) {
            Bundle id = getIntent().getExtras();
            if (id != null) {
                EspressoIdlingResource.increment();

                int movieId = id.getInt("movieId");
                movieViewModel = obtainMovieViewModel(this, getApplicationContext());
                movieViewModel.getMovieById(movieId).observe(this, movie -> {
                    isFavourite = RoomConfig.getInstance(this).movieDao().findMovieById(movie.getId()) != null;
                    setDataDetail(
                            movie.getTitle(),
                            movie.getReleaseDate(),
                            movie.getVoteAverage(),
                            movie.getOverview(),
                            movie.getPosterPath()
                    );
                    thisMovie = movie;
                    setFavourite();

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().show();
                    }

                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                });
            }
        } else {
            Bundle id = getIntent().getExtras();
            if (id != null) {
                EspressoIdlingResource.increment();

                int tvShowId = id.getInt("tvShowId");
                tvShowViewModel = obtainTvShowViewModel(this, getApplicationContext());
                tvShowViewModel.getTvShowById(tvShowId).observe(this, tvShow -> {
                    isFavourite = RoomConfig.getInstance(this).tvShowDao().findTvShowById(tvShow.getId()) != null;
                    setDataDetail(
                            tvShow.getName(),
                            tvShow.getFirstAirDate(),
                            tvShow.getVoteAverage(),
                            tvShow.getOverview(),
                            tvShow.getPosterPath()
                    );
                    thisTvShow = tvShow;
                    setFavourite();

                    if (getSupportActionBar() != null) {
                        getSupportActionBar().show();
                    }

                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                });
            }
        }
    }

    private void setDataDetail(String title, String release, float vote, String overview, String poster) {
        textViewTitleDetail.setText(title);
        textViewReleaseDetail.setText(release);
        textViewScoreDetail.setText(String.valueOf(vote));
        textViewOverviewDetail.setText(overview);
        Glide.with(getApplicationContext()).load(ApiConstants.API_POSTER + poster).into(imageViewDetail);
    }

    private void addOrRemoveToFavourite() {
        if (isFavourite) {
            if (isMovie()) {
                movieViewModel.deleteMovieFromFavourite(thisMovie);
            } else {
                tvShowViewModel.deleteTvShowFromFavourite(thisTvShow);
            }
            Toast.makeText(this, getString(R.string.remove_from_favourite), Toast.LENGTH_SHORT).show();
        } else {
            if (isMovie()) {
                movieViewModel.insertMovieToFavourite(thisMovie);
            } else {
                tvShowViewModel.insertTvShowToFavourite(thisTvShow);
            }
            Toast.makeText(this, getString(R.string.add_to_favourite), Toast.LENGTH_SHORT).show();
        }

        isFavourite = !isFavourite;
    }

    private void setFavourite() {
        if (isFavourite) {
            menuItem.getItem(0).setIcon(R.drawable.icon_added_to_favourite);
        } else {
            menuItem.getItem(0).setIcon(R.drawable.icon_add_to_favourite);
        }
    }

    private boolean isMovie() {
        return getIntent().getBooleanExtra("isMovie", true);
    }
}
