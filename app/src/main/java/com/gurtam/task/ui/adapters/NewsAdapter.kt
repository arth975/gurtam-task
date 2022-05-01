package com.gurtam.task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gurtam.task.databinding.ItemNewsBinding
import com.gurtam.task.models.NewsUI

class NewsAdapter(
    itemCallback: NewsItemDiffCallback
) : PagingDataAdapter<NewsUI, NewsAdapter.NewsViewHolder>(itemCallback) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemNewsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class NewsViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(news: NewsUI?) {
            news?.let {
                binding.news = news
            }
        }
    }
}

class NewsItemDiffCallback : DiffUtil.ItemCallback<NewsUI>() {
    override fun areItemsTheSame(oldItem: NewsUI, newItem: NewsUI): Boolean {
        return oldItem.url == newItem.url
    }

    override fun areContentsTheSame(oldItem: NewsUI, newItem: NewsUI): Boolean {
        return oldItem == newItem
    }

}