package com.eddy.myexam.main.viewmodel.image

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.eddy.myexam.db.ImageItem
import com.eddy.myexam.extension.sendWithMain
import com.eddy.myexam.model.ImageResponse
import com.eddy.myexam.model.Resource
import com.eddy.myexam.repository.IImageDbRepository
import com.eddy.myexam.repository.IPixabayRepository
import com.eddy.myexam.utils.Event
import com.eddy.myexam.utils.SpUtils
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.concurrent.atomic.AtomicBoolean
import javax.inject.Inject

@HiltViewModel
class SearchImagesViewModel @Inject constructor(
    private val context: Application,
    val imageDbRepository: IImageDbRepository,
    val pixabayRepository: IPixabayRepository,
) : ViewModel() {

    private val _searchForImage = MutableLiveData<Event<Resource<ImageResponse>>>()
    val searchForImage: LiveData<Event<Resource<ImageResponse>>> = _searchForImage

    private val _saveImage = MutableLiveData<Event<Resource<ImageResponse>>>()
    val saveImage: LiveData<Event<Resource<ImageResponse>>> = _saveImage
    var isGridLayout:AtomicBoolean? = null
    var currentImageUrl = ""

    suspend fun searchForImage(imageQuery: String) = withContext(Dispatchers.IO) {
        if (imageQuery.isEmpty()) {
            return@withContext
        }
        _searchForImage.sendWithMain(Resource.loading(null).toEvent())
        val response = pixabayRepository.searchForImage(imageQuery)
        _searchForImage.sendWithMain(response.toEvent())
    }

    suspend fun saveImage(note: String) = withContext(Dispatchers.IO) {
        if (note.isEmpty()) {
            _saveImage.sendWithMain(Resource.error("please enter a note.", null).toEvent())
            return@withContext
        }

        if (currentImageUrl.isEmpty()) {
            _saveImage.sendWithMain(Resource.error("url is empty.", null).toEvent())
            return@withContext
        }

        _saveImage.sendWithMain(Resource.loading(null).toEvent())
        val item = ImageItem(note, currentImageUrl)
        imageDbRepository.insertItem(item)
        _saveImage.sendWithMain(Resource.success(null).toEvent())
    }

    fun getHistories(): List<String> {
        return SpUtils.getHistories(context)
    }

    fun setHistories(list: MutableList<String>) {
        if (list.count() > 5) {
            list.removeLast()
        }
        SpUtils.setHistories(context, list)
    }
}