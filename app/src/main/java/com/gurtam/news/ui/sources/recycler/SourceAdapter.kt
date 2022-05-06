package com.gurtam.news.ui.sources.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.gurtam.news.databinding.ItemSourceBinding
import com.gurtam.news.domain.entity.Source

class SourceAdapter(
    private val onClickListener: ((Source) -> Unit)
) : ListAdapter<Source, SourceAdapter.SourceViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SourceViewHolder {
        val binding = ItemSourceBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return SourceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SourceViewHolder, position: Int) {
        holder.onBind(currentList[position])
    }

    inner class SourceViewHolder(private val binding: ItemSourceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(source: Source) {
            binding.tvTitle.text = source.name
            binding.tvSubtitle.text = source.description
            binding.root.setOnClickListener {
                onClickListener.invoke(source)
            }
        }
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Source>() {
            override fun areItemsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Source, newItem: Source): Boolean {
                return oldItem == newItem
            }
        }
    }
}