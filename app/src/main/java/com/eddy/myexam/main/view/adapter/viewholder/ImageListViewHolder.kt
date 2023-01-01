package com.eddy.myexam.main.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemImageListBinding
import com.eddy.myexam.model.ImageResponse

class ImageListViewHolder(private val binding: ItemImageListBinding, private val glide: RequestManager) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ImageResponse.ImageResult, onItemClickListener: ((String) -> Unit)?) {
        binding.apply {
            glide.load(data.previewURL).into(image)
            itemLayout.setOnClickListener {
                onItemClickListener?.invoke(data.previewURL)
            }
            userLabel.text = data.user
            viewsLabel.text = data.views.toString()
            downloadLabel.text = data.downloads.toString()
        }
    }
}

