package com.example.bug;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.not;

/**
 * Must be run on a device/emulator with animations turned off
 */
@RunWith(AndroidJUnit4.class)
public class BugTest {

    @Rule
    public ActivityTestRule<BugActivity> rule = new ActivityTestRule<>(BugActivity.class, true, false);

    @Test // Always PASSES
    public void testPropertyAnimation() throws Exception {
        Intent intent = new Intent();
        intent.putExtra(BugActivity.FLING, false);
        rule.launchActivity(intent);

        onView(withId(R.id.bug)).perform(click());
        onView(withId(R.id.bug)).check(matches(not(isDisplayed())));
    }


    @Test // Always FAILS
    public void testFlingAnimation() throws Exception {
        Intent intent = new Intent();
        intent.putExtra(BugActivity.FLING, true);
        rule.launchActivity(intent);


        onView(withId(R.id.bug)).perform(click());
        onView(withId(R.id.bug)).check(matches(not(isDisplayed())));
    }

}
