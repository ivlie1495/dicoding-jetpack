package com.example.submissionjetpack.config;

import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.MovieResponse;
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.model.TvShowResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("movie/upcoming")
    Call<MovieResponse> getListMovie();

    @GET("discover/tv")
    Call<TvShowResponse> getListTvShow();

    @GET("movie/{movie_id}")
    Call<Movie> getMovieById(@Path("movie_id") int movieId);

    @GET("tv/{tv_id}")
    Call<TvShow> getTvShowById(@Path("tv_id") int tvId);
}
