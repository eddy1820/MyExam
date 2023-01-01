package com.eddy.myexam.repository

import com.eddy.myexam.manager.ErrorCodeManager
import com.eddy.myexam.model.ImageResponse
import com.eddy.myexam.model.Resource
import com.eddy.myexam.service.PixabayService
import javax.inject.Inject

class PixabayRepository @Inject constructor(private val service: PixabayService) : IPixabayRepository {
    override suspend fun searchForImage(imageQuery: String): Resource<ImageResponse> {
        return ErrorCodeManager.errorCodeHandel({
            service.searchForImage(imageQuery)
        }) {
        }
    }
}