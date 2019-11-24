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
import com.example.submissionjetpack.adapter.TvShowAdapter;
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.viewmodel.TvShowViewModel;
import com.example.submissionjetpack.viewmodel.ViewModelFactory;

import java.util.List;

public class TvShowFragment extends Fragment {

    private RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tv_show, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewForTvShow);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            TvShowViewModel tvShowViewModel = obtainViewModel(getActivity(), getContext());

            EspressoIdlingResource.increment();
            MutableLiveData<List<TvShow>> mutableLiveDataMovies = tvShowViewModel.getTvShowList();
            mutableLiveDataMovies.observe(this, tvShows -> {
                if (tvShows.size() > 0) {
                    TvShowAdapter tvShowAdapter = new TvShowAdapter(tvShows, getContext());
                    recyclerView.setAdapter(tvShowAdapter);

                    if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                        EspressoIdlingResource.decrement();
                    }
                }
            });
        }
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
    }
}
