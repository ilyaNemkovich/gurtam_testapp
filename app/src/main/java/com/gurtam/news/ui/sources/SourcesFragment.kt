package com.gurtam.news.ui.sources

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gurtam.news.databinding.FragmentSourcesBinding
import com.gurtam.news.domain.entity.Source
import com.gurtam.news.ui.headlines.HeadlinesFragment
import com.gurtam.news.ui.sources.recycler.SourceAdapter
import com.gurtam.news.ui.util.NavigationManager
import com.gurtam.news.ui.util.ViewBindingFragment
import com.gurtam.news.ui.util.requireListener
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SourcesFragment : ViewBindingFragment<FragmentSourcesBinding>(FragmentSourcesBinding::class) {

    private val viewModel: SourcesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val sourceAdapter = SourceAdapter { openHeadlinesFragment(it) }
        binding.rvSources.adapter = sourceAdapter
        viewModel.source.observe(viewLifecycleOwner) {
            sourceAdapter.submitList(it)
        }
    }

    private fun openHeadlinesFragment(source: Source) {
        requireListener<NavigationManager>().openFragment(HeadlinesFragment.newInstance(source))
    }

    companion object {
        fun newInstance() = SourcesFragment()
    }
}