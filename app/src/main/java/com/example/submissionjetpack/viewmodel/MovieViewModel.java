package com.example.submissionjetpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.data.source.DataRepository;

import java.util.List;

public class MovieViewModel extends ViewModel {

    private DataRepository dataRepository;

    MovieViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public MutableLiveData<List<Movie>> getMovieList() {
        return dataRepository.getMovies();
    }

    public MutableLiveData<Movie> getMovieById(int movieId) {
        return dataRepository.getMovieById(movieId);
    }

    public LiveData<List<Movie>> getFavouriteMovieList() {
        return dataRepository.getFavouriteMovieList();
    }

    public void insertMovieToFavourite(Movie movie) {
        dataRepository.addFavouriteMovie(movie);
    }

    public void deleteMovieFromFavourite(Movie movie) {
        dataRepository.deleteFavouriteMovie(movie);
    }

    public LiveData<PagedList<Movie>> getAllFavouriteMovies() {
        return new LivePagedListBuilder<>(dataRepository.getAllFavouriteMovies(), 20).build();
    }
}
