package com.eddy.myexam.main.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.lifecycleScope
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.eddy.myexam.R
import com.eddy.myexam.db.ImageItem
import com.eddy.myexam.getOrAwaitValue
import com.eddy.myexam.main.view.adapter.viewholder.ImageInfoViewHolder
import com.eddy.myexam.main.viewmodel.MainViewModel
import com.eddy.myexam.utils.MyViewAction
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.launch
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test


@ExperimentalCoroutinesApi
@MediumTest
@HiltAndroidTest
class MainActivityTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @After
    fun tearDown() {
    }

    @Test
    fun testRemoveItemFromList() {
        val testActivityScenario = ActivityScenario.launch(MainActivity::class.java)
        var testViewModel: MainViewModel? = null
        testActivityScenario.onActivity {
            testViewModel = it.viewModel
            it.lifecycleScope.launch {
                it.viewModel.imageDbRepository.insertItem(ImageItem("url1", "note1"))
            }
        }
        assertThat(testViewModel?.imageItems?.getOrAwaitValue()?.count()).isEqualTo(1)
        onView(withId(R.id.recyclerView)).perform(
            RecyclerViewActions.actionOnItemAtPosition<ImageInfoViewHolder>(
                0,
                MyViewAction.clickChildViewWithId(R.id.deleteBtn)
            )
        )
        assertThat(testViewModel?.imageItems?.getOrAwaitValue()).isEmpty()
    }

    @Test
    fun testOpenSearchImagePage() {

        val testActivityScenario = ActivityScenario.launch(MainActivity::class.java)
        onView(withId(R.id.addImagesFab)).check(matches(isDisplayed()))
        onView(withId(R.id.addImagesFab)).perform(click())
        pressBack()
        onView(withId(R.id.addImagesFab)).check(matches(isDisplayed()))
    }
}