package com.example.submissionjetpack.data.local;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.example.submissionjetpack.config.RoomConfig;
import com.example.submissionjetpack.dao.MovieDao;
import com.example.submissionjetpack.dao.TvShowDao;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.TvShow;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class LocalRepository {

    private MovieDao movieDao;
    private TvShowDao tvShowDao;
    private ExecutorService executorService;
    private static LocalRepository localRepository;

    public static LocalRepository getInstance(Context context) {
        if (localRepository == null) {
            localRepository = new LocalRepository(context);
        }

        return localRepository;
    }

    private LocalRepository(Context context) {
        executorService = Executors.newSingleThreadExecutor();

        RoomConfig config = RoomConfig.getInstance(context);
        movieDao = config.movieDao();
        tvShowDao = config.tvShowDao();
    }

    public LiveData<List<Movie>> getFavouriteMovieList() {
        return movieDao.getFavouriteMovieList();
    }

    public void addMovieFavourite(Movie movie) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.addToFavourite(movie);
            }
        });
    }

    public void deleteMovieFavourite(Movie movie) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                movieDao.deleteFromFavourite(movie);
            }
        });
    }

    public LiveData<List<TvShow>> getFavouriteTvShowList() {
        return tvShowDao.getFavouriteTvShowList();
    }

    public void addTvShowFavourite(TvShow tvShow) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                tvShowDao.addToFavourite(tvShow);
            }
        });
    }

    public void deleteTvShowFavourite(TvShow tvShow) {
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                tvShowDao.deleteFromFavourite(tvShow);
            }
        });
    }

    public DataSource.Factory<Integer, Movie> getAllFavouriteMovies() {
        return movieDao.getAllFavouriteMovies();
    }

    public DataSource.Factory<Integer, TvShow> getAllFavouriteTvShows() {
        return tvShowDao.getAllFavouriteTvShows();
    }
}
