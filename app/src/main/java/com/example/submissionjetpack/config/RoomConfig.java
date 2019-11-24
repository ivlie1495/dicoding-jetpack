package com.example.submissionjetpack.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.submissionjetpack.dao.MovieDao;
import com.example.submissionjetpack.dao.TvShowDao;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.model.TvShow;

@Database(entities = {Movie.class, TvShow.class}, version = 1)
public abstract class RoomConfig extends RoomDatabase {

    public abstract MovieDao movieDao();
    public abstract TvShowDao tvShowDao();
    private static RoomConfig INSTANCE;

    public static RoomConfig getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context, RoomConfig.class, "db.Favourite")
                    .allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return INSTANCE;
    }
}
