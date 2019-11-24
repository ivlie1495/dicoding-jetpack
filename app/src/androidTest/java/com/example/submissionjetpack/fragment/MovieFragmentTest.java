package com.example.submissionjetpack.fragment;

import androidx.test.espresso.IdlingRegistry;
import androidx.test.rule.ActivityTestRule;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.SingleFragmentActivity;
import com.example.submissionjetpack.utils.EspressoIdlingResource;
import com.example.submissionjetpack.utils.RecyclerViewItemCountAssertion;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

public class MovieFragmentTest {

    @Rule
    public ActivityTestRule<SingleFragmentActivity> activityRule = new ActivityTestRule<>(SingleFragmentActivity.class);
    private MovieFragment movieFragment = new MovieFragment();

    @Before
    public void setUp() {
        activityRule.getActivity().setFragment(movieFragment);
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void loadMovie() {
        onView(withId(R.id.recyclerViewForMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewForMovie)).check(new RecyclerViewItemCountAssertion(20));
    }
}