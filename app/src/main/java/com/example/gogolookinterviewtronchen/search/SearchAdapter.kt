package com.example.gogolookinterviewtronchen.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.gogolookinterviewtronchen.data.History
import com.example.gogolookinterviewtronchen.data.SearchImage
import com.example.gogolookinterviewtronchen.databinding.ItemListHistoryBinding
import com.example.gogolookinterviewtronchen.databinding.ItemListImageBinding

class SearchAdapter(private val itemClickListener: HistoryOnItemClickListener)
    : ListAdapter<History, RecyclerView.ViewHolder>(HistoryDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HistoryViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is HistoryViewHolder ->{
                val history = item as History
                holder.bind(history)
                holder.binding.conHistory.setOnClickListener {
                    itemClickListener.onItemClicked(history)
                }
            }
        }

    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<History>() {
        override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
            return oldItem.date == newItem.date
        }
    }

    class HistoryViewHolder(val binding: ItemListHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(history: History) {
            binding.history = history
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): HistoryViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListHistoryBinding.inflate(layoutInflater, parent, false)
                return HistoryViewHolder(binding)
            }
        }
    }

    class HistoryOnItemClickListener(val clickListener: (history: History) -> Unit ){
        fun onItemClicked(history: History) = clickListener(history)
    }
}
