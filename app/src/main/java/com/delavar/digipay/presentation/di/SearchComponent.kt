package com.delavar.digipay.presentation.di

import com.delavar.digipay.data.repository.SearchRepositoryImpl
import com.delavar.digipay.domain.repository.SearchRepository
import com.delavar.digipay.domain.usecase.SearchUseCase

class SearchComponent (appComponent: AppComponent) : AppComponent by appComponent {

    val searchRepository: SearchRepository by lazy {
        SearchRepositoryImpl(cloudSource)
    }

    val searchUseCase: SearchUseCase by lazy {
        SearchUseCase(domainScheduler, searchRepository)
    }
}