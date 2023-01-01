package com.eddy.myexam.di

import com.eddy.myexam.service.PixabayService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object ServiceModule {
    @Provides
    @Singleton
    fun providePixabayService(retrofit: Retrofit) = retrofit.create(PixabayService::class.java)
}