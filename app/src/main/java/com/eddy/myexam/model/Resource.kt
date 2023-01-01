package com.eddy.myexam.model

import com.eddy.myexam.utils.Event

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T?): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    fun toEvent() = Event(this)
    fun isSuccess() = status == Status.SUCCESS
    fun isError() = status == Status.ERROR
    fun isLoading() = status == Status.LOADING
}

enum class Status {
    SUCCESS,
    ERROR,
    LOADING
}