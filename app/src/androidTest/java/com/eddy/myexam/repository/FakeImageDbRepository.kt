package com.eddy.myexam.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.eddy.myexam.db.ImageItem
import javax.inject.Inject

class FakeImageDbRepository @Inject constructor() : IImageDbRepository {
    private val items = mutableListOf<ImageItem>()

    private val observableImageItems = MutableLiveData<List<ImageItem>>(items)
    override suspend fun insertItem(imageItem: ImageItem) {
        items.add(imageItem)
    }

    override suspend fun deleteItem(imageItem: ImageItem) {
        items.remove(imageItem)
    }

    override fun observeAllImagesItems(): LiveData<List<ImageItem>> {
        return observableImageItems
    }

}