package com.eddy.myexam.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "image_item")
data class ImageItem(
    var note: String,
    var imgUrl: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null
)
