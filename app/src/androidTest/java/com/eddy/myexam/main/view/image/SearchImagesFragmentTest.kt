package com.eddy.myexam.main.view.image

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.eddy.myexam.R
import com.eddy.myexam.launchFragmentInHiltContainer
import com.eddy.myexam.main.view.adapter.viewholder.ImageInfoViewHolder
import com.eddy.myexam.utils.RecyclerViewItemCountAssertion
import com.eddy.myexam.utils.SpUtils
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.verify


@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class SearchImagesFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    lateinit var navController: NavController

    @Before
    fun setUp() {
        hiltRule.inject()
        navController = Mockito.mock(NavController::class.java)
        val context = ApplicationProvider.getApplicationContext<Context>()
        SpUtils.setHistories(context, listOf())
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testSearchImages() {
        var fragment: SearchImagesFragment? = null
        launchFragmentInHiltContainer<SearchImagesFragment> {
            fragment = this
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.searchEdit)).perform(replaceText("qa"), closeSoftKeyboard())
        onView(withId(R.id.searchBtn)).perform(click())
        onView(withId(R.id.list)).check(RecyclerViewItemCountAssertion(5))
        onView(withId(R.id.list)).perform(RecyclerViewActions.actionOnItemAtPosition<ImageInfoViewHolder>(0, click()))
        verify(navController).navigate(SearchImagesFragmentDirections.actionSearchImagesFragmentToAddImagesFragment())
    }

    @Test
    fun testHistories() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        SpUtils.setHistories(context, listOf("dog", "cat", "car"))
        var fragment: SearchImagesFragment? = null
        launchFragmentInHiltContainer<SearchImagesFragment> {
            fragment = this
            Navigation.setViewNavController(requireView(), navController)
        }

        onView(withId(R.id.searchEdit)).perform(typeText("aaa"), closeSoftKeyboard())
        onView(withId(R.id.historiesList)).check(RecyclerViewItemCountAssertion(3))
        onView(withId(R.id.historiesList)).perform(RecyclerViewActions.actionOnItemAtPosition<ImageInfoViewHolder>(0, click()))
        onView(withId(R.id.searchEdit)).check(matches(withText("dog")))
        SpUtils.setHistories(context, listOf())
    }
}