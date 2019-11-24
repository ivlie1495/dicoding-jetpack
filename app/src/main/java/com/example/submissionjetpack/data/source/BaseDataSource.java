package com.example.submissionjetpack.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.TvShow;

import java.util.List;

public interface BaseDataSource {
    MutableLiveData<List<Movie>> getMovies();
    MutableLiveData<Movie> getMovieById(int movieId);
    MutableLiveData<List<TvShow>> getTvShows();
    MutableLiveData<TvShow> getTvShowById(int tvShowId);
    LiveData<List<Movie>> getFavouriteMovieList();
    LiveData<List<TvShow>> getFavouriteTvShowList();
    void addFavouriteMovie(Movie movie);
    void deleteFavouriteMovie(Movie movie);
    void addFavouriteTvShow(TvShow tvShow);
    void deleteFavouriteTvShow(TvShow tvShow);

    DataSource.Factory<Integer, Movie> getAllFavouriteMovies();
    DataSource.Factory<Integer, TvShow> getAllFavouriteTvShows();
}
