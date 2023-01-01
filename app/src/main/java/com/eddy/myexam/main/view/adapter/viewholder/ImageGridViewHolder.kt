package com.eddy.myexam.main.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemImageGridBinding
import com.eddy.myexam.model.ImageResponse

class ImageGridViewHolder(private val binding: ItemImageGridBinding, private val glide: RequestManager) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ImageResponse.ImageResult, onItemClickListener: ((String) -> Unit)?) {
        binding.apply {
            glide.load(data.previewURL).into(image)
            image.setOnClickListener {
                onItemClickListener?.invoke(data.previewURL)
            }
        }
    }
}

