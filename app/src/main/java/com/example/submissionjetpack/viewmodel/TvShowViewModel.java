package com.example.submissionjetpack.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.data.source.DataRepository;

import java.util.List;

public class TvShowViewModel extends ViewModel {

    private DataRepository dataRepository;

    TvShowViewModel(DataRepository dataRepository) {
        this.dataRepository = dataRepository;
    }

    public MutableLiveData<List<TvShow>> getTvShowList() {
        return dataRepository.getTvShows();
    }

    public MutableLiveData<TvShow> getTvShowById(int tvShowId) {
        return dataRepository.getTvShowById(tvShowId);
    }

    public LiveData<List<TvShow>> getFavouriteTvShowList() {
        return dataRepository.getFavouriteTvShowList();
    }

    public void insertTvShowToFavourite(TvShow tvShow) {
        dataRepository.addFavouriteTvShow(tvShow);
    }

    public void deleteTvShowFromFavourite(TvShow tvShow) {
        dataRepository.deleteFavouriteTvShow(tvShow);
    }

    public LiveData<PagedList<TvShow>> getAllFavouriteTvShows() {
        return new LivePagedListBuilder<>(dataRepository.getAllFavouriteTvShows(), 20).build();
    }
}
