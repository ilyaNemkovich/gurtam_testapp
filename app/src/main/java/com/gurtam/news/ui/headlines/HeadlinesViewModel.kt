package com.gurtam.news.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gurtam.news.domain.NewsInteractor
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _headlines = MutableLiveData<List<Headline>>()
    val headlines: LiveData<List<Headline>> = _headlines

    private val _source = MutableLiveData<Source>()
    val source: LiveData<Source> = _source

    fun setSource(source: Source) {
        _source.value = source
        viewModelScope.launch {
            _headlines.value = newsInteractor.getHeadlines(source)
        }
    }
}