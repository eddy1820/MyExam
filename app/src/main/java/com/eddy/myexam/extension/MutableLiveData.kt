package com.eddy.myexam.extension

import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> MutableLiveData<T>.sendWithMain(response: T) {
    withContext(Dispatchers.Main) {
        This@value = response
    }
}
