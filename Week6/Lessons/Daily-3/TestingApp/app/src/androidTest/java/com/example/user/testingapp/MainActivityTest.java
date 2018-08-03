package com.example.user.testingapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    @Rule
    public ActivityTestRule<MainActivity> activityActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        System.out.println("MainActivityTest.setUp");
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("MainActivityTest.tearDown");
    }

    @Test
    public void onUpdateTextView() {
        System.out.println("MainActivityTest.onUpdateTextView");

        //find the EditText amd update test
        onView(withId(R.id.etMain))
                .perform(typeText("something"), closeSoftKeyboard());

        //perform the button click
        onView(withId(R.id.btnMain))
                .perform(click());

        //assert(check) the textview for the text entered
        onView(withId(R.id.tvMain))
            .check(matches(withText("something")));
    }
}