package com.gurtam.task.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.gurtam.task.databinding.ItemNewsSourceBinding
import com.gurtam.task.models.NewsSourceUI

class NewsSourceAdapter(
    private val onItemClick: (NewsSourceUI) -> Unit
) : RecyclerView.Adapter<NewsSourceAdapter.SourceViewHolder>() {

    var newsSources: List<NewsSourceUI> = emptyList()
        set(newValue) {
            val diffResult = DiffUtil.calculateDiff(NewsSourceDiffCallback(field, newValue))
            diffResult.dispatchUpdatesTo(this)
            field = newValue
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        return SourceViewHolder(
            ItemNewsSourceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.bind(newsSources[position])
    }

    override fun getItemCount(): Int = newsSources.size

    inner class SourceViewHolder(private val binding: ItemNewsSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(newsSourceItem: NewsSourceUI) {
            with(binding) {
                newsSource = newsSourceItem
                root.setOnClickListener { onItemClick(newsSourceItem) }
            }
        }
    }
}

class NewsSourceDiffCallback(
    private val oldList: List<NewsSourceUI>,
    private val newList: List<NewsSourceUI>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition].id == newList[newItemPosition].id

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        oldList[oldItemPosition] == newList[newItemPosition]
}