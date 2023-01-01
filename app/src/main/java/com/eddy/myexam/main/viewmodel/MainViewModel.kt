package com.eddy.myexam.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eddy.myexam.db.ImageItem
import com.eddy.myexam.repository.IImageDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val imageDbRepository: IImageDbRepository,
) : ViewModel() {

    val imageItems = imageDbRepository.observeAllImagesItems()

    fun deleteImageItem(imageItem: ImageItem) = viewModelScope.launch {
        imageDbRepository.deleteItem(imageItem)
    }

}