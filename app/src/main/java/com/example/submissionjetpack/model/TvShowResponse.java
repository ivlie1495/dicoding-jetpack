package com.example.submissionjetpack.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TvShowResponse {

    @SerializedName("results")
    private List<TvShow> getTvShowList;

    public List<TvShow> getTvShowList() {
        return getTvShowList;
    }
}
