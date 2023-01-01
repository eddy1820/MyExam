package com.eddy.myexam.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.filters.SmallTest
import com.eddy.myexam.getOrAwaitValue
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Named

@ExperimentalCoroutinesApi
@SmallTest
@HiltAndroidTest
class ImageDaoTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Inject
    @Named("test_db")
    lateinit var database: ImageItemDatabase
    private lateinit var dao: ImageDao


    @Before
    fun setup() {
        hiltRule.inject()
        dao = database.imageDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertShoppingItem() = runTest {
        val item = ImageItem("dog", "url", 1)
        dao.insertImageItem(item)
        // the liveData is asynchronous by default, so we need to add rule
        val list = dao.observeAllImageItems().getOrAwaitValue()
        assertThat(list).contains(item)
    }

    @Test
    fun deleteShoppingItem() = runTest {
        val item = ImageItem("dog", "url", 1)
        dao.insertImageItem(item)
        dao.deleteImageItem(item)
        val list = dao.observeAllImageItems().getOrAwaitValue()
        assertThat(list).doesNotContain(item)
    }
}