package com.eddy.myexam.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemImageGridBinding
import com.eddy.myexam.databinding.ItemImageListBinding
import com.eddy.myexam.main.view.adapter.viewholder.ImageGridViewHolder
import com.eddy.myexam.main.view.adapter.viewholder.ImageListViewHolder
import com.eddy.myexam.model.ImageResponse
import javax.inject.Inject

class ImageAdapter @Inject constructor(
    private val glide: RequestManager
) : ListAdapter<ImageResponse.ImageResult, RecyclerView.ViewHolder>(diffUtil) {

    var gridCount = 1
    private val VIEW_TYPE_LIST = 1
    private val VIEW_TYPE_GRID = 2

    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<ImageResponse.ImageResult>() {
            override fun areItemsTheSame(oldItem: ImageResponse.ImageResult, newItem: ImageResponse.ImageResult): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ImageResponse.ImageResult, newItem: ImageResponse.ImageResult): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == VIEW_TYPE_LIST) {
            ImageListViewHolder(ItemImageListBinding.inflate(LayoutInflater.from(parent.context), parent, false), glide)
        } else {
            ImageGridViewHolder(ItemImageGridBinding.inflate(LayoutInflater.from(parent.context), parent, false), glide)
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null

    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ImageGridViewHolder -> holder.bind(currentList[position], onItemClickListener)
            is ImageListViewHolder -> holder.bind(currentList[position], onItemClickListener)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (gridCount != 1) VIEW_TYPE_GRID else VIEW_TYPE_LIST
    }
}















