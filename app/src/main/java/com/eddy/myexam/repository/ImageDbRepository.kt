package com.eddy.myexam.repository

import androidx.lifecycle.LiveData
import com.eddy.myexam.db.ImageDao
import com.eddy.myexam.db.ImageItem
import javax.inject.Inject

class ImageDbRepository @Inject constructor(
    private val imageDao: ImageDao,
) : IImageDbRepository {
    override suspend fun insertItem(imageItem: ImageItem) {
        imageDao.insertImageItem(imageItem)
    }

    override suspend fun deleteItem(imageItem: ImageItem) {
        imageDao.deleteImageItem(imageItem)
    }

    override fun observeAllImagesItems(): LiveData<List<ImageItem>> {
        return imageDao.observeAllImageItems()
    }

}