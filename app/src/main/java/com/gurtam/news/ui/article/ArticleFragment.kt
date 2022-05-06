package com.gurtam.news.ui.article

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.gurtam.news.databinding.FragmentArticleBinding
import com.gurtam.news.ui.util.ViewBindingFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ArticleFragment : ViewBindingFragment<FragmentArticleBinding>(FragmentArticleBinding::class) {

    private val viewModel: ArticleViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewSetup()
        subToLiveData()
        setListeners()
    }

    private fun setListeners() {

    }

    private fun viewSetup() {

    }

    private fun subToLiveData() {

    }

    companion object {
        fun newInstance() = ArticleFragment()
    }
}