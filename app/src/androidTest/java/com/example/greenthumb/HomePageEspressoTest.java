package com.example.greenthumb;

import android.view.View;

import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Rule;
import org.junit.Test;

import static androidx.test.espresso.Espresso.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class HomePageEspressoTest {

    @Rule
    public ActivityScenarioRule<HomePage> activityScenarioRule
            = new ActivityScenarioRule<>(HomePage.class);

    @Test
    public void verifyNavigationDisplayed() {
        onView(withId(R.id.mainNav))
                .check(matches(isDisplayed()));
        onView(withId(R.id.toHomePage))
                .check(matches(isDisplayed()));
        onView(withId(R.id.toTaskPage))
                .check(matches(isDisplayed()));
    }

    @Test
    //check that navigation bar can take user from Home Page -> Tasks
    public void testViewTasksPage() {
        onView(withId(R.id.toTaskPage))
                .perform(click());
        intended(hasComponent(ViewTasks.class.getName()));
    }

}
