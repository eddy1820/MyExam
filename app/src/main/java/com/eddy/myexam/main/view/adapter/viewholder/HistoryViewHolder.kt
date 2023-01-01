package com.eddy.myexam.main.view.adapter.viewholder

import androidx.recyclerview.widget.RecyclerView
import com.eddy.myexam.databinding.ItemHistoryBinding

class HistoryViewHolder(private val binding: ItemHistoryBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(text: String, onItemClickListener: ((String) -> Unit)?) {
        binding.historyLabel.text = text
        binding.layout.setOnClickListener { onItemClickListener?.invoke(text) }
    }
}