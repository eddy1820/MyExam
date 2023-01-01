package com.eddy.myexam.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemImageInfoBinding
import com.eddy.myexam.db.ImageItem
import com.eddy.myexam.main.view.adapter.viewholder.ImageInfoViewHolder
import javax.inject.Inject


class ImagesSelectedAdapter @Inject constructor(val glide: RequestManager) : ListAdapter<ImageItem, RecyclerView.ViewHolder>(diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ImageItem>() {
            override fun areItemsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImageItem, newItem: ImageItem): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }

    private var onItemClickListener: ((ImageItem) -> Unit)? = null
    fun setOnItemClickListener(listener: (ImageItem) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageInfoViewHolder(ItemImageInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false), glide)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? ImageInfoViewHolder)?.bind(currentList[position], onItemClickListener)
    }
}


