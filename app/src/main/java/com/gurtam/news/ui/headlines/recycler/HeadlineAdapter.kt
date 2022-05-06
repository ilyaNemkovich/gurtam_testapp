package com.gurtam.news.ui.headlines.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gurtam.news.databinding.ItemHeadlineBinding
import com.gurtam.news.domain.entity.Headline

class HeadlineAdapter(
    private val onClickListener: ((Headline) -> Unit)
) : ListAdapter<Headline, HeadlineAdapter.HeadlineViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val binding = ItemHeadlineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadlineViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class HeadlineViewHolder(private val binding: ItemHeadlineBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(headline: Headline) {
            binding.tvTitle.text = headline.title
            binding.tvSubtitle.text = headline.description
            binding.ivImage.load(headline.urlToImage)
            binding.tvDate.text = headline.publishedAt
            binding.root.setOnClickListener {
                onClickListener.invoke(headline)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Headline>() {
            override fun areItemsTheSame(oldItem: Headline, newItem: Headline): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Headline, newItem: Headline): Boolean {
                return oldItem == newItem
            }
        }
    }
}