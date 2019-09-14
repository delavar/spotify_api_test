package com.delavar.digipay.presentation.ui.search

import com.delavar.digipay.domain.model.Artist
import androidx.lifecycle.LiveData
import com.delavar.digipay.domain.response.ErrorModel
import com.delavar.digipay.domain.usecase.SearchUseCase
import com.delavar.digipay.presentation.ui.base.BaseViewModel
import com.delavar.digipay.presentation.utils.livedata.NonNullLiveData

class SearchViewModel(val searchUseCase: SearchUseCase) : BaseViewModel() {
    private val _items: NonNullLiveData<List<Artist>> = NonNullLiveData(emptyList())
    var items: LiveData<List<Artist>> = _items.apply { value = emptyList() }

    fun onResponse(response: List<Artist>?): Unit {
        val list = ArrayList(_items.value)
        list.addAll(response ?: emptyList())
        _items.value = list
    }

    fun onError(error: ErrorModel?): Unit {

    }

    fun search(query: String?) {
        query?.let {
            searchUseCase.setParam(query, 20, 0).execute(compositeDisposable, this::onResponse, this::onError)
        }
    }
}