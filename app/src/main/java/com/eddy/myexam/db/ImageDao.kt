package com.eddy.myexam.db

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ImageDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertImageItem(imageItem: ImageItem)

    @Delete
    suspend fun deleteImageItem(imageItem: ImageItem)

    @Query("SELECT * FROM image_item")
    fun observeAllImageItems(): LiveData<List<ImageItem>>
}