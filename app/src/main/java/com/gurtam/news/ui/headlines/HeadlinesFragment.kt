package com.gurtam.news.ui.headlines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.gurtam.news.databinding.FragmentHeadlinesBinding
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import com.gurtam.news.ui.headlines.recycler.HeadlineAdapter
import com.gurtam.news.ui.headlines.recycler.HeadlinePagingAdapter
import com.gurtam.news.ui.util.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeadlinesFragment :
    ViewBindingFragment<FragmentHeadlinesBinding>(FragmentHeadlinesBinding::class) {

    private val viewModel: HeadlinesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().getParcelable<Source>("source")?.apply {
            viewModel.setSource(this)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (viewModel.isPagingEnabled) {
            val headlineAdapter = HeadlinePagingAdapter { openArticleFragment(it) }.apply {
                binding.rvSources.adapter = this
            }
            lifecycleScope.launch {
                viewModel.headlinesFlow?.collectLatest { pagingData ->
                    headlineAdapter.submitData(pagingData)
                }
            }
        } else {
            val headlineAdapter = HeadlineAdapter { openArticleFragment(it) }.apply {
                binding.rvSources.adapter = this
            }
            viewModel.headlines.observe(viewLifecycleOwner) {
                headlineAdapter.submitList(it)
            }
        }
    }

    private fun openArticleFragment(headline: Headline) {
        Toast.makeText(requireContext(), headline.toString(), Toast.LENGTH_SHORT).show()
    }

    companion object {
        fun newInstance(source: Source) = HeadlinesFragment().apply {
            arguments = Bundle().apply {
                putParcelable("source", source)
            }
        }
    }
}