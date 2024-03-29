package com.example.submissionjetpack.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MovieResponse {

    @SerializedName("results")
    private List<Movie> getMovieList;

    public List<Movie> getMovieList() {
        return getMovieList;
    }
}
