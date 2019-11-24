package com.example.submissionjetpack.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.data.source.DataRepository;
import com.example.submissionjetpack.utils.LiveDataTestUtil;
import com.example.submissionjetpack.utils.PagedListUtil;
import com.example.submissionjetpack.vo.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DataRepository dataRepository;
    private TvShowViewModel tvShowViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataRepository = mock(DataRepository.class);
        tvShowViewModel = new TvShowViewModel(dataRepository);
    }

    @Test
    public void getTvShowList() {
        MutableLiveData<List<TvShow>> mutableLiveDataTvShows = new MutableLiveData<>();
        List<TvShow> tvShowList = new ArrayList<>();
        tvShowList.add(new TvShow(1, "ABC", "asd", "qwe", "jkl", "123", 10));
        mutableLiveDataTvShows.setValue(tvShowList);

        when(dataRepository.getTvShows()).thenReturn(mutableLiveDataTvShows);
        Observer<List<TvShow>> observer = mock(Observer.class);
        tvShowViewModel.getTvShowList().observeForever(observer);
        verify(observer).onChanged(tvShowList);
        verify(dataRepository).getTvShows();

        List<TvShow> testTvShowList = LiveDataTestUtil.getValue(dataRepository.getTvShows());
        Assert.assertNotNull(testTvShowList);
    }

    @Test
    public void getTvShowById() {
        MutableLiveData<TvShow> mutableLiveDataTvShow = new MutableLiveData<>();
        TvShow tvShow = new TvShow(1, "ABC", "asd", "qwe", "jkl", "123", 10);
        mutableLiveDataTvShow.setValue(tvShow);

        when(dataRepository.getTvShowById(62286)).thenReturn(mutableLiveDataTvShow);
        Observer<TvShow> observer = mock(Observer.class);
        tvShowViewModel.getTvShowById(62286).observeForever(observer);
        verify(observer).onChanged(tvShow);
        verify(dataRepository).getTvShowById(62286);

        TvShow testTvShow = LiveDataTestUtil.getValue(dataRepository.getTvShowById(62286));
        Assert.assertNotNull(testTvShow);
    }

    @Test
    public void getFavouriteListTvShow() {
        MutableLiveData<List<TvShow>> mutableLiveDataTvShows = new MutableLiveData<>();
        List<TvShow> tvShowList = new ArrayList<>();
        tvShowList.add(new TvShow(1, "ABC", "asd", "qwe", "jkl", "123", 10));
        mutableLiveDataTvShows.setValue(tvShowList);

        when(dataRepository.getFavouriteTvShowList()).thenReturn(mutableLiveDataTvShows);
        Observer<List<TvShow>> observer = mock(Observer.class);
        tvShowViewModel.getFavouriteTvShowList().observeForever(observer);
        verify(observer).onChanged(tvShowList);
        verify(dataRepository).getFavouriteTvShowList();

        List<TvShow> testTvShowList = LiveDataTestUtil.getValue(dataRepository.getFavouriteTvShowList());
        Assert.assertNotNull(testTvShowList);
    }

    @Test
    public void getFavouriteTvShowsWithPagination() {
        DataSource.Factory<Integer, TvShow> dataSourceTvShow = mock(DataSource.Factory.class);
        when(dataRepository.getAllFavouriteTvShows()).thenReturn(dataSourceTvShow);

        List<TvShow> tvShowList = new ArrayList<>();
        tvShowList.add(new TvShow(1, "ABC", "asd", "qwe", "jkl", "123", 10));

        dataRepository.getAllFavouriteTvShows();
        Resource<PagedList<TvShow>> result = Resource.success(PagedListUtil.mockPagedList(tvShowList));

        verify(dataRepository).getAllFavouriteTvShows();
        Assert.assertNotNull(result.data);
        Assert.assertEquals(tvShowList.size(), result.data.size());
    }
}