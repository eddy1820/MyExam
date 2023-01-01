package com.eddy.myexam.di

import android.content.Context
import androidx.room.Room
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.eddy.myexam.BuildConfig
import com.eddy.myexam.R
import com.eddy.myexam.base.BaseApplication
import com.eddy.myexam.db.ImageItemDatabase
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.net.HttpURLConnection
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    const val DATABASE_NAME = "image_db"

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): BaseApplication {
        return app as BaseApplication
    }

    @Provides
    fun provideBaseUrl() = BuildConfig.BASE_URL

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val builder: OkHttpClient.Builder = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .connectTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(RedirectInterceptor())
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(HttpLoggingInterceptor()
                .apply { setLevel(HttpLoggingInterceptor.Level.BODY) })
        }
        return builder.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(okHttpClient: OkHttpClient, BASE_URL: String): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    @Singleton
    @Provides
    fun provideGlideInstance(
        @ApplicationContext context: Context
    ) = Glide.with(context).setDefaultRequestOptions(
        RequestOptions()
            .placeholder(R.drawable.ic_image)
            .error(R.drawable.ic_image)
    )

    @Singleton
    @Provides
    fun provideImageItemDatabase(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(context, ImageItemDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun provideImageDao(
        database: ImageItemDatabase
    ) = database.imageDao()

}

private fun createResponse(chain: Interceptor.Chain, request: Request): Response {
    return chain.proceed(
        request.newBuilder()
            .method(request.method, request.body).build()
    )
}

class RedirectInterceptor : Interceptor {
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val response: Response = createResponse(chain, chain.request())
        when (response.code) {
            HttpURLConnection.HTTP_OK -> {
            }
            HttpURLConnection.HTTP_UNAUTHORIZED -> {
            }
            HttpURLConnection.HTTP_FORBIDDEN -> {
            }
        }
        return response
    }

}