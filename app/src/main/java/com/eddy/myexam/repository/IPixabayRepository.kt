package com.eddy.myexam.repository

import com.eddy.myexam.model.ImageResponse
import com.eddy.myexam.model.Resource

interface IPixabayRepository {
    suspend fun searchForImage(imageQuery: String): Resource<ImageResponse>
}