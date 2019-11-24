package com.example.submissionjetpack.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;

import com.example.submissionjetpack.data.local.LocalRepository;
import com.example.submissionjetpack.data.remote.RemoteRepository;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.TvShow;

import java.util.List;

public class DataRepository implements BaseDataSource {

    private static DataRepository dataRepository;
    private RemoteRepository remoteRepository;
    private LocalRepository localRepository;

    private DataRepository(RemoteRepository remoteRepository, LocalRepository localRepository) {
        this.remoteRepository = remoteRepository;
        this.localRepository = localRepository;
    }

    public static DataRepository getInstance(RemoteRepository remoteRepository, LocalRepository localRepository) {
        if (dataRepository == null) {
            synchronized (DataRepository.class) {
                if (dataRepository == null) {
                    dataRepository = new DataRepository(remoteRepository, localRepository);
                }
            }
        }

        return dataRepository;
    }

    @Override
    public MutableLiveData<List<Movie>> getMovies() {
        return remoteRepository.getListMovie();
    }

    @Override
    public MutableLiveData<Movie> getMovieById(int movieId) {
        return remoteRepository.getMovie(movieId);
    }

    @Override
    public MutableLiveData<List<TvShow>> getTvShows() {
        return remoteRepository.getListTvShow();
    }

    @Override
    public MutableLiveData<TvShow> getTvShowById(int tvShowId) {
        return remoteRepository.getTvShow(tvShowId);
    }

    @Override
    public LiveData<List<Movie>> getFavouriteMovieList() {
        return localRepository.getFavouriteMovieList();
    }

    @Override
    public LiveData<List<TvShow>> getFavouriteTvShowList() {
        return localRepository.getFavouriteTvShowList();
    }

    @Override
    public void addFavouriteMovie(Movie movie) {
        localRepository.addMovieFavourite(movie);
    }

    @Override
    public void deleteFavouriteMovie(Movie movie) {
        localRepository.deleteMovieFavourite(movie);
    }

    @Override
    public void addFavouriteTvShow(TvShow tvShow) {
        localRepository.addTvShowFavourite(tvShow);
    }

    @Override
    public void deleteFavouriteTvShow(TvShow tvShow) {
        localRepository.deleteTvShowFavourite(tvShow);
    }

    @Override
    public DataSource.Factory<Integer, Movie> getAllFavouriteMovies() {
        return localRepository.getAllFavouriteMovies();
    }

    @Override
    public DataSource.Factory<Integer, TvShow> getAllFavouriteTvShows() {
        return localRepository.getAllFavouriteTvShows();
    }
}
