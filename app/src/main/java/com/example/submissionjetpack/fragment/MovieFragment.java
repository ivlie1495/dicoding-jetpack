package com.example.submissionjetpack.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.adapter.MovieAdapter;
import com.example.submissionjetpack.model.Movie;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.viewmodel.MovieViewModel;
import com.example.submissionjetpack.viewmodel.ViewModelFactory;

import java.util.List;

public class MovieFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewForMovie);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            MovieViewModel movieViewModel = obtainViewModel(getActivity(), getContext());

            EspressoIdlingResource.increment();
            MutableLiveData<List<Movie>> mutableLiveDataMovies = movieViewModel.getMovieList();
            mutableLiveDataMovies.observe(this, movies -> {
                if (movies.size() > 0) {
                    MovieAdapter movieAdapter = new MovieAdapter(movies, getContext());
                    recyclerView.setAdapter(movieAdapter);

                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }
            });
        }
    }

    @NonNull
    private static MovieViewModel obtainViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(MovieViewModel.class);
    }
}
