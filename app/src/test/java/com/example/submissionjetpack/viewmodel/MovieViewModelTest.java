package com.example.submissionjetpack.viewmodel;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.example.submissionjetpack.model.Movie;
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

public class MovieViewModelTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private DataRepository dataRepository;
    private MovieViewModel movieViewModel;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        dataRepository = mock(DataRepository.class);
        movieViewModel = new MovieViewModel(dataRepository);
    }

    @Test
    public void getMovieList() {
        MutableLiveData<List<Movie>> mutableLiveDataMovies = new MutableLiveData<>();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1, "ABC", "asd", "qwe", "jkl", "123", 10));
        mutableLiveDataMovies.setValue(movieList);

        when(dataRepository.getMovies()).thenReturn(mutableLiveDataMovies);
        Observer<List<Movie>> observer = mock(Observer.class);
        movieViewModel.getMovieList().observeForever(observer);
        verify(observer).onChanged(movieList);
        verify(dataRepository).getMovies();

        List<Movie> testMovieList = LiveDataTestUtil.getValue(dataRepository.getMovies());
        Assert.assertNotNull(testMovieList);
    }

    @Test
    public void getMovieById() {
        MutableLiveData<Movie> mutableLiveDataMovie = new MutableLiveData<>();
        Movie movie = new Movie(1, "ABC", "asd", "qwe", "jkl", "123", 10);
        mutableLiveDataMovie.setValue(movie);

        when(dataRepository.getMovieById(290859)).thenReturn(mutableLiveDataMovie);
        Observer<Movie> observer = mock(Observer.class);
        movieViewModel.getMovieById(290859).observeForever(observer);
        verify(observer).onChanged(movie);
        verify(dataRepository).getMovieById(290859);

        Movie testMovie = LiveDataTestUtil.getValue(dataRepository.getMovieById(290859));
        Assert.assertNotNull(testMovie);
    }

    @Test
    public void getFavouriteListMovie() {
        MutableLiveData<List<Movie>> mutableLiveDataMovies = new MutableLiveData<>();
        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1, "ABC", "asd", "qwe", "jkl", "123", 10));
        mutableLiveDataMovies.setValue(movieList);

        when(dataRepository.getFavouriteMovieList()).thenReturn(mutableLiveDataMovies);
        Observer<List<Movie>> observer = mock(Observer.class);
        movieViewModel.getFavouriteMovieList().observeForever(observer);
        verify(observer).onChanged(movieList);
        verify(dataRepository).getFavouriteMovieList();

        List<Movie> testMovieList = LiveDataTestUtil.getValue(dataRepository.getFavouriteMovieList());
        Assert.assertNotNull(testMovieList);
    }

    @Test
    public void getFavouriteTvShowsWithPagination() {
        DataSource.Factory<Integer, Movie> dataSourceMovie = mock(DataSource.Factory.class);
        when(dataRepository.getAllFavouriteMovies()).thenReturn(dataSourceMovie);

        List<Movie> movieList = new ArrayList<>();
        movieList.add(new Movie(1, "ABC", "asd", "qwe", "jkl", "123", 10));

        dataRepository.getAllFavouriteTvShows();
        Resource<PagedList<Movie>> result = Resource.success(PagedListUtil.mockPagedList(movieList));

        verify(dataRepository).getAllFavouriteTvShows();
        Assert.assertNotNull(result.data);
        Assert.assertEquals(movieList.size(), result.data.size());
    }
}