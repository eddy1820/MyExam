package com.eddy.myexam.repository

import androidx.lifecycle.LiveData
import com.eddy.myexam.db.ImageItem

interface IImageDbRepository {
    suspend fun insertItem(imageItem: ImageItem)

    suspend fun deleteItem(imageItem: ImageItem)

    fun observeAllImagesItems(): LiveData<List<ImageItem>>
}