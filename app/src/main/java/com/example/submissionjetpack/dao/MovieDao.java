package com.example.submissionjetpack.dao;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.submissionjetpack.model.Movie;

import java.util.List;

@Dao
public interface MovieDao {

    @Query("SELECT * FROM Movie")
    LiveData<List<Movie>> getFavouriteMovieList();

    @Query("SELECT * FROM Movie WHERE id = :id")
    Movie findMovieById(int id);

    @Insert
    void addToFavourite(Movie movie);

    @Delete
    void deleteFromFavourite(Movie movie);

    @Query("SELECT * FROM Movie ORDER BY id ASC")
    DataSource.Factory<Integer, Movie> getAllFavouriteMovies();
}
