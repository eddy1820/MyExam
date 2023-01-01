package com.eddy.myexam.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [ImageItem::class],
    version = 1
)
abstract class ImageItemDatabase : RoomDatabase() {
    abstract fun imageDao(): ImageDao
}