package com.example.submissionjetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.submissionjetpack.model.TvShow;

import java.util.List;

@Dao
public interface TvShowDao {

    @Query("SELECT * FROM TvShow")
    LiveData<List<TvShow>> getFavouriteTvShowList();

    @Query("SELECT * FROM TvShow WHERE id = :id")
    TvShow findTvShowById(int id);

    @Insert
    void addToFavourite(TvShow tvShow);

    @Delete
    void deleteFromFavourite(TvShow tvShow);

    @Query("SELECT * FROM TvShow ORDER BY id ASC")
    DataSource.Factory<Integer, TvShow> getAllFavouriteTvShows();
}
