package com.example.submissionjetpack.view;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.rule.ActivityTestRule;

import com.example.submissionjetpack.R;
import com.example.submissionjetpack.utils.EspressoIdlingResource;

import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.AllOf.allOf;

public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.getIdlingResource());
    }

    @After
    public void tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.getIdlingResource());
    }

    @Test
    public void activityTest() {
        //check bottom navigation
        onView(withId(R.id.bottomNavigation)).check(matches(isDisplayed()));
        onView(withId(R.id.navigationTV)).perform(click());
        onView(withId(R.id.navigationFavorite)).perform(click());
        onView(withId(R.id.navigationMovie)).perform(click());

        //check movies
        onView(withId(R.id.recyclerViewForMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewForMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));
        onView(withId(R.id.textViewScoreDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).perform(click());
        Espresso.pressBack();

        //check tv shows
        onView(withId(R.id.navigationTV)).perform(click());
        onView(withId(R.id.recyclerViewForTvShow)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewForTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(3, click()));
        onView(withId(R.id.textViewTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewScoreDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).perform(click());
        Espresso.pressBack();

        //check favourites
        onView(withId(R.id.navigationFavorite)).perform(click());
        onView(withId(R.id.tabLayoutFavourite)).check(matches(isDisplayed()));
        Matcher<View> tabMovie = allOf(withText("Movie"), isDescendantOfA(withId(R.id.tabLayoutFavourite)));
        Matcher<View> tabTvShow = allOf(withText("Tv Show"), isDescendantOfA(withId(R.id.tabLayoutFavourite)));
        onView(tabTvShow).perform(click());
        onView(withId(R.id.recyclerViewFavouriteTvShow)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewFavouriteTvShow)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.textViewTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewScoreDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).perform(click());
        Espresso.pressBack();

        onView(tabMovie).perform(click());
        onView(withId(R.id.recyclerViewFavouriteMovie)).check(matches(isDisplayed()));
        onView(withId(R.id.recyclerViewFavouriteMovie)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
        onView(withId(R.id.textViewTitleDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewScoreDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewOverviewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewReleaseDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.imageViewDetail)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).check(matches(isDisplayed()));
        onView(withId(R.id.action_favourite)).perform(click());
        Espresso.pressBack();
    }
}