package com.example.submissionjetpack.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.adapter.FavouriteMovieAdapter;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.viewmodel.MovieViewModel;
import com.example.submissionjetpack.viewmodel.ViewModelFactory;

public class FavouriteMovieFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private MovieViewModel movieViewModel;
    private FavouriteMovieAdapter favouriteMovieAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_movie, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewFavouriteMovie);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshFavouriteMovie);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            movieViewModel = obtainViewModel(getActivity(), getContext());
            getData();
        }

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(false);
        getData();
    }

    @Override
    public void onResume() {
        super.onResume();
        getData();
    }

    private void getData() {
        LiveData<PagedList<Movie>> mutableLiveDataMovies = movieViewModel.getAllFavouriteMovies();

        EspressoIdlingResource.increment();
        mutableLiveDataMovies.observe(this, movies -> {
            favouriteMovieAdapter = new FavouriteMovieAdapter();
            favouriteMovieAdapter.submitList(movies);
            recyclerView.setAdapter(favouriteMovieAdapter);

            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        });
    }
}
