package com.example.submissionjetpack.data.remote;

import androidx.lifecycle.MutableLiveData;

import com.example.submissionjetpack.config.Api;
import com.example.submissionjetpack.config.ApiConfig;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.MovieResponse;
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.model.TvShowResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RemoteRepository {

    private static RemoteRepository remoteRepository;
    private Api api;

    public static RemoteRepository getInstance() {
        if (remoteRepository == null) {
            remoteRepository = new RemoteRepository();
        }

        return remoteRepository;
    }

    private RemoteRepository() {
        api = ApiConfig.getService();
    }

    public MutableLiveData<List<Movie>> getListMovie() {
        MutableLiveData<List<Movie>> movieList = new MutableLiveData<>();

        api.getListMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                if (response.body() != null) {
                    movieList.setValue(response.body().getMovieList());
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return movieList;
    }

    public MutableLiveData<Movie> getMovie(int movieId) {
        MutableLiveData<Movie> movie = new MutableLiveData<>();

        api.getMovieById(movieId).enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response) {
                if (response.body() != null) {
                    movie.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<Movie> call, Throwable t) {

            }
        });

        return movie;
    }

    public MutableLiveData<List<TvShow>> getListTvShow() {
        MutableLiveData<List<TvShow>> tvShowList = new MutableLiveData<>();

        api.getListTvShow().enqueue(new Callback<TvShowResponse>() {
            @Override
            public void onResponse(Call<TvShowResponse> call, Response<TvShowResponse> response) {
                if (response.body() != null) {
                    tvShowList.setValue(response.body().getTvShowList());
                }
            }

            @Override
            public void onFailure(Call<TvShowResponse> call, Throwable t) {

            }
        });

        return tvShowList;
    }

    public MutableLiveData<TvShow> getTvShow(int tvShowId) {
        MutableLiveData<TvShow> tvShow = new MutableLiveData<>();

        api.getTvShowById(tvShowId).enqueue(new Callback<TvShow>() {
            @Override
            public void onResponse(Call<TvShow> call, Response<TvShow> response) {
                if (response.body() != null) {
                    tvShow.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<TvShow> call, Throwable t) {

            }
        });

        return tvShow;
    }
}
