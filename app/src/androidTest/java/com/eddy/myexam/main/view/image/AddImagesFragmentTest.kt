package com.eddy.myexam.main.view.image

import android.app.Activity
import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.filters.MediumTest
import com.eddy.myexam.R
import com.eddy.myexam.getOrAwaitValue
import com.eddy.myexam.launchFragmentInHiltContainer
import com.eddy.myexam.utils.SpUtils
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

@MediumTest
@ExperimentalCoroutinesApi
@HiltAndroidTest
class AddImagesFragmentTest {


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
    fun testPressSaveWhenNoteIsEmpty() {
        launchFragmentInHiltContainer<AddImagesFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(CoreMatchers.allOf(withId(com.google.android.material.R.id.snackbar_text), withText("please enter a note")))
    }

    @Test
    fun testSaveImage() {
        var fragment: AddImagesFragment? = null
        var activity: Activity? = null
        launchFragmentInHiltContainer<AddImagesFragment> {
            fragment = this
            activity = this.requireActivity()
            Navigation.setViewNavController(requireView(), navController)
        }
        fragment?.viewModel?.currentImageUrl = "url"
        onView(withId(R.id.noteEdit)).perform(replaceText("qqqq"), ViewActions.closeSoftKeyboard())
        onView(withId(R.id.saveBtn)).perform(click())
        assertThat(fragment?.viewModel?.imageDbRepository?.observeAllImagesItems()?.getOrAwaitValue()?.count()).isEqualTo(1)
        assertThat(activity?.isFinishing).isTrue()
    }
}