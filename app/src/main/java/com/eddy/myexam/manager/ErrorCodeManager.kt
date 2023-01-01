package com.eddy.myexam.manager

import com.eddy.myexam.model.Resource
import com.google.gson.JsonSyntaxException
import retrofit2.Response
import java.io.IOException

object ErrorCodeManager {
    suspend fun <T> errorCodeHandel(requestCallback: suspend () -> Response<T>, onSuccessfulCallback: (T?) -> Unit): Resource<T> {
        return try {
            val response = requestCallback.invoke()
            if (response.isSuccessful) {
                onSuccessfulCallback.invoke(response.body())
                return Resource.success(response.body())
            } else {
                Resource.error("An unknown error occurred", null)
            }
        } catch (e: Exception) {
            when (e) {
                is IOException -> "Couldn't reach the server. Check your internet connection"
                is JsonSyntaxException -> "JsonSyntaxException"
                else -> "An unknown error occurred"
            }.let {
                Resource.error(it, null)
            }
        }
    }
}