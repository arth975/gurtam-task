package com.gurtam.task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gurtam.task.databinding.ItemArticleBinding
import com.gurtam.task.models.ArticleUI

class ArticlesPagingDataAdapter(
    private val onItemClick: (ArticleUI) -> Unit,
    itemCallback: ArticleItemDiffCallback = ArticleItemDiffCallback()
) : PagingDataAdapter<ArticleUI, ArticlesPagingDataAdapter.NewsViewHolder>(itemCallback) {

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        return NewsViewHolder(
            ItemArticleBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    inner class NewsViewHolder(private val binding: ItemArticleBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(article: ArticleUI?) {
            article?.let {
                binding.article = article
                binding.root.setOnClickListener { onItemClick(article) }
            }
        }
    }
}

class ArticleItemDiffCallback : DiffUtil.ItemCallback<ArticleUI>() {
    override fun areItemsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem.url == newItem.url

    override fun areContentsTheSame(oldItem: ArticleUI, newItem: ArticleUI): Boolean =
        oldItem == newItem
}