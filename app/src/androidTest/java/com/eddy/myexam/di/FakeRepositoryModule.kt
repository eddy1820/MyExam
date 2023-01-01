package com.eddy.myexam.di

import com.eddy.myexam.repository.FakeImageDbRepository
import com.eddy.myexam.repository.FakePixabayRepository
import com.eddy.myexam.repository.IImageDbRepository
import com.eddy.myexam.repository.IPixabayRepository
import com.eddy.myexam.service.PixabayService
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(components = [SingletonComponent::class], replaces = [RepositoryModule::class])
object FakeRepositoryModule {
    @Provides
    fun provideFakePixabayRepository() = FakePixabayRepository() as IPixabayRepository

    @Provides
    fun provideFakeImageDbRepository() = FakeImageDbRepository() as IImageDbRepository
}