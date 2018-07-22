package com.tapdevs.kotlin.views.fragments

import android.support.test.InstrumentationRegistry
import android.support.test.espresso.Espresso
import android.support.test.espresso.assertion.ViewAssertions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v4.app.Fragment
import com.tapdevs.kotlin.R
import com.tapdevs.kotlin.views.activities.MainActivity
import junit.framework.Assert
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class FruitsFragmentTest {

    @get:Rule
    val activityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule<MainActivity>(MainActivity::class.java, true, true)

    lateinit var fruitsFragment: FruitsFragment

    @Before
    fun setUp() {
        fruitsFragment = FruitsFragment()
    }

    fun attachFragment(containsReadItems : Boolean): Fragment {
        val instrumentation = InstrumentationRegistry.getInstrumentation()

        //This has to be run on main thread, or 'findFragmentByTag' will return null
        instrumentation.runOnMainSync {
            val fragMan = activityTestRule.activity.supportFragmentManager
            fragMan.beginTransaction().apply {
                add(R.id.fragment_content, fruitsFragment, "FruitsFragment")
            }.commit()
            activityTestRule.activity.supportFragmentManager.executePendingTransactions()
        }

        return activityTestRule.activity.supportFragmentManager.findFragmentByTag("FruitsFragment")
    }

    @Test
    fun fragment_NotNull() {
        Assert.assertNotNull(fruitsFragment)
    }

    @Test
    fun check_ifFragmentFrameIsVisible() {
        Espresso.onView(ViewMatchers.withId(R.id.fragment_content)).check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

}