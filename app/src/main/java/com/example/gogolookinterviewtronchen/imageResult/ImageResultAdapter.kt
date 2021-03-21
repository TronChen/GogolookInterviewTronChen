package com.example.gogolookinterviewtronchen.imageResult

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.gogolookinterviewtronchen.data.SearchImage
import com.example.gogolookinterviewtronchen.databinding.ItemListImageBinding


class ImageResultAdapter()
    : PagedListAdapter<SearchImage, RecyclerView.ViewHolder>(ImageRDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ImageRViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when (holder) {
            is ImageRViewHolder ->{
                val searchImage = item as SearchImage
                holder.bind(searchImage)
            }
        }

    }

    class ImageRDiffCallback : DiffUtil.ItemCallback<SearchImage>() {
        override fun areItemsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: SearchImage, newItem: SearchImage): Boolean {
            return oldItem.id == newItem.id
        }
    }

    class ImageRViewHolder(val binding: ItemListImageBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(searchImage: SearchImage) {
            binding.searchImage = searchImage
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
        companion object {
            fun from(parent: ViewGroup): ImageRViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListImageBinding.inflate(layoutInflater, parent, false)
                return ImageRViewHolder(binding)
            }
        }
    }
}
