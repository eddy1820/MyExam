package com.eddy.myexam.main.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemImageInfoBinding
import com.eddy.myexam.db.ImageItem

class ImageInfoViewHolder(private val binding: ItemImageInfoBinding, private val glide: RequestManager) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: ImageItem, onItemClickListener: ((ImageItem) -> Unit)?) {
        binding.deleteBtn.setOnClickListener {
            onItemClickListener?.invoke(data)
        }
        binding.noteLabel.text = data.note
        glide.load(data.imgUrl)
            .centerCrop()
            .into(binding.image)
    }
}