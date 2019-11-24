package com.example.submissionjetpack.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.adapter.ViewPagerAdapter;
import com.example.submissionjetpack.fragment.FavouriteFragment;
import com.example.submissionjetpack.fragment.MovieFragment;
import com.example.submissionjetpack.fragment.TvShowFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    BottomNavigationView bottomNavigationView;
    ViewPager viewPager;

    private MenuItem menuItem;

    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {
                case R.id.navigationMovie:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.navigationTV:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.navigationFavorite:
                    viewPager.setCurrentItem(2);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);
        viewPager = findViewById(R.id.viewPager);

        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);
        if (savedInstanceState == null) {
            bottomNavigationView.setSelectedItemId(R.id.navigationMovie);
        }

        viewPager.addOnPageChangeListener(this);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addTabFragment(new MovieFragment());
        adapter.addTabFragment(new TvShowFragment());
        adapter.addTabFragment(new FavouriteFragment());
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (menuItem != null) {
            menuItem.setChecked(false);
        } else {
            bottomNavigationView.getMenu().getItem(0).setChecked(false);
        }
        bottomNavigationView.getMenu().getItem(position).setChecked(true);
        menuItem = bottomNavigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
