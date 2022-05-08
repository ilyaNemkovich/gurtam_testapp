package com.gurtam.news.ui.headlines.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.gurtam.news.databinding.ItemHeadlineBinding
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.ui.headlines.recycler.HeadlinePagingAdapter.Companion.DIFF_CALLBACK
import com.gurtam.news.ui.headlines.recycler.HeadlinePagingAdapter.HeadlineViewHolder

class HeadlineAdapter(
    private val onClickListener: ((Headline) -> Unit)
) : ListAdapter<Headline, HeadlineViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeadlineViewHolder {
        val binding = ItemHeadlineBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return HeadlineViewHolder(binding, onClickListener)
    }

    override fun onBindViewHolder(holder: HeadlineViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }
}