package com.gurtam.news.ui.sources

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gurtam.news.domain.NewsInteractor
import com.gurtam.news.domain.entity.Source
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SourcesViewModel @Inject constructor(
    private val newsInteractor: NewsInteractor
) : ViewModel() {

    private val _source = MutableLiveData<List<Source>>()
    val source: LiveData<List<Source>> = _source

    init {
        viewModelScope.launch {
            try {
                _source.value = newsInteractor.getSources()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}