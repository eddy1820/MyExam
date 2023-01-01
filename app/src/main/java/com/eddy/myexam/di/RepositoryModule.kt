package com.eddy.myexam.di

import com.eddy.myexam.db.ImageDao
import com.eddy.myexam.repository.IImageDbRepository
import com.eddy.myexam.repository.IPixabayRepository
import com.eddy.myexam.repository.ImageDbRepository
import com.eddy.myexam.repository.PixabayRepository
import com.eddy.myexam.service.PixabayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Singleton
    @Provides
    fun providePixabayRepository(service: PixabayService) = PixabayRepository(service) as IPixabayRepository

    @Singleton
    @Provides
    fun provideImageDbRepository(dao: ImageDao) = ImageDbRepository(dao) as IImageDbRepository
}