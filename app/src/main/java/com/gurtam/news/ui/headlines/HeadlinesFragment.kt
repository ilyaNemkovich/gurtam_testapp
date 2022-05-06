package com.gurtam.news.ui.headlines

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.gurtam.news.databinding.FragmentHeadlinesBinding
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import com.gurtam.news.ui.headlines.recycler.HeadlineAdapter
import com.gurtam.news.ui.util.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

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
        val headlineAdapter = HeadlineAdapter { openArticleFragment(it) }
        binding.rvSources.adapter = headlineAdapter
        viewModel.headlines.observe(viewLifecycleOwner) {
            headlineAdapter.submitList(it)
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