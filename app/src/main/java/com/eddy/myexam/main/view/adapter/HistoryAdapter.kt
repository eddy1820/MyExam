package com.eddy.myexam.main.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.RequestManager
import com.eddy.myexam.databinding.ItemHistoryBinding
import com.eddy.myexam.databinding.ItemImageInfoBinding
import com.eddy.myexam.db.ImageItem
import com.eddy.myexam.main.view.adapter.viewholder.HistoryViewHolder
import com.eddy.myexam.main.view.adapter.viewholder.ImageInfoViewHolder
import javax.inject.Inject


class HistoryAdapter @Inject constructor(val glide: RequestManager) : ListAdapter<String, RecyclerView.ViewHolder>(diffUtil) {
    companion object {
        private val diffUtil = object : DiffUtil.ItemCallback<String>() {
            override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
                return oldItem == newItem
            }
        }
    }

    private var onItemClickListener: ((String) -> Unit)? = null
    fun setOnItemClickListener(listener: (String) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as? HistoryViewHolder)?.bind(currentList[position], onItemClickListener)
    }
}


