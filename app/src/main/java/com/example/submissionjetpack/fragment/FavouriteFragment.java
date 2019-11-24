package com.example.submissionjetpack.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class FavouriteFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favourite, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ViewPager viewPager = view.findViewById(R.id.viewPageFavourite);
        TabLayout tabLayout = view.findViewById(R.id.tabLayoutFavourite);

        tabLayout.setupWithViewPager(viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addTabFragment(new FavouriteMovieFragment(), getString(R.string.movie));
        adapter.addTabFragment(new FavouriteTvShowFragment(), getString(R.string.tvShow));
        viewPager.setAdapter(adapter);
    }
}
