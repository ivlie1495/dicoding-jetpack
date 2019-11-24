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
import com.example.submissionjetpack.adapter.FavouriteTvShowAdapter;
import com.example.submissionjetpack.model.TvShow;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.viewmodel.TvShowViewModel;
import com.example.submissionjetpack.viewmodel.ViewModelFactory;

public class FavouriteTvShowFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TvShowViewModel tvShowViewModel;
    private FavouriteTvShowAdapter favouriteTvShowAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favourite_tv_show, container, false);
        recyclerView = view.findViewById(R.id.recyclerViewFavouriteTvShow);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshFavouriteTvShow);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (getActivity() != null) {
            tvShowViewModel = obtainViewModel(getActivity(), getContext());
            getData();
        }

        swipeRefreshLayout.setOnRefreshListener(this);
    }

    @NonNull
    private static TvShowViewModel obtainViewModel(FragmentActivity activity, Context context) {
        ViewModelFactory factory = ViewModelFactory.getInstance(context);
        return ViewModelProviders.of(activity, factory).get(TvShowViewModel.class);
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
        LiveData<PagedList<TvShow>> mutableLiveDataTvShow = tvShowViewModel.getAllFavouriteTvShows();

        EspressoIdlingResource.increment();
        mutableLiveDataTvShow.observe(this, tvShows -> {
            favouriteTvShowAdapter = new FavouriteTvShowAdapter();
            favouriteTvShowAdapter.submitList(tvShows);
            recyclerView.setAdapter(favouriteTvShowAdapter);

            if (!EspressoIdlingResource.getIdlingResource().isIdleNow()) {
                EspressoIdlingResource.decrement();
            }
        });
    }
}
