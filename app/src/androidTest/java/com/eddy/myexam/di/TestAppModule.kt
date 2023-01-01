package com.eddy.myexam.di

import android.content.Context
import androidx.room.Room
import com.eddy.myexam.db.ImageItemDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object TestAppModule {

    @Provides
    @Named("test_db")
    fun provideInMemoryDb(@ApplicationContext context: Context) =
        Room.inMemoryDatabaseBuilder(context, ImageItemDatabase::class.java)
            .allowMainThreadQueries()
            .build()
}