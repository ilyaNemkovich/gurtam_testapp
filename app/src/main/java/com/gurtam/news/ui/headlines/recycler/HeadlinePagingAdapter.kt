package com.gurtam.news.ui.headlines.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.gurtam.news.databinding.ItemHeadlineBinding
import com.gurtam.news.domain.entity.Headline

class HeadlinePagingAdapter(
    private val onClickListener: ((Headline) -> Unit)
) : PagingDataAdapter<Headline, HeadlinePagingAdapter.HeadlineViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val binding = ItemHeadlineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadlineViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.onBind(getItem(position))
    }

    class HeadlineViewHolder(
        private val binding: ItemHeadlineBinding,
        private val onClickListener: ((Headline) -> Unit)
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(headline: Headline?) {
            binding.tvTitle.text = headline?.title ?: ""
            binding.tvSubtitle.text = headline?.description ?: ""
            binding.ivImage.load(headline?.urlToImage)
            binding.tvDate.text = headline?.publishedAt ?: ""
            binding.root.setOnClickListener {
                if (headline != null) onClickListener.invoke(headline)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Headline>() {
            override fun areItemsTheSame(oldItem: Headline, newItem: Headline): Boolean {
                return oldItem.url == newItem.url
            }

            override fun areContentsTheSame(oldItem: Headline, newItem: Headline): Boolean {
                return oldItem == newItem
            }
        }
    }
}