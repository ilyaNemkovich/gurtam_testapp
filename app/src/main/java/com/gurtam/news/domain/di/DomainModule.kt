package com.gurtam.news.domain.di

import com.gurtam.news.domain.NewsInteractor
import com.gurtam.news.domain.NewsInteractorImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindNewsInteractor(impl: NewsInteractorImpl): NewsInteractor
}