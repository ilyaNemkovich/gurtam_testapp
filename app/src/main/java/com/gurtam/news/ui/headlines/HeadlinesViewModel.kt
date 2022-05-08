package com.gurtam.news.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.gurtam.news.domain.NewsInteractor
import com.gurtam.news.domain.entity.Headline
import com.gurtam.news.domain.entity.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlinesViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    // PAGING quickly reaches the api free usage quota
    val isPagingEnabled = false

    private val _headlines = MutableLiveData<List<Headline>>()
    val headlines: LiveData<List<Headline>> = _headlines

    private val _source = MutableLiveData<Source>()
    val source: LiveData<Source> = _source

    var headlinesFlow: Flow<PagingData<Headline>>? = null
        private set

    fun setSource(source: Source) {
        _source.value = source
        if (isPagingEnabled) {
            if (headlinesFlow != null) return
            headlinesFlow = Pager(PagingConfig(pageSize = 5)) {
                newsInteractor.getHeadlinesDataSource(source)
            }.flow.cachedIn(viewModelScope)
        } else {
            viewModelScope.launch {
                _headlines.value = newsInteractor.getHeadlines(source)
            }
        }
    }
}