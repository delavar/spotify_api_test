package com.delavar.digipay.presentation.ui.search

import com.delavar.digipay.domain.model.Artist
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.delavar.digipay.domain.response.ErrorModel
import com.delavar.digipay.domain.response.ErrorStatus
import com.delavar.digipay.domain.usecase.SearchUseCase
import com.delavar.digipay.presentation.ui.base.BaseViewModel
import com.delavar.digipay.presentation.utils.livedata.NonNullLiveData
import com.delavar.digipay.presentation.utils.livedata.SingleEventLiveData

class SearchViewModel(val searchUseCase: SearchUseCase) : BaseViewModel() {
    private val _items: NonNullLiveData<List<Artist>> = NonNullLiveData(emptyList())
    var items: LiveData<List<Artist>> = _items.apply { value = emptyList() }

    private val _message: MutableLiveData<String> = SingleEventLiveData<String>()
    val message: LiveData<String> = _message

    private val _unauthorized: MutableLiveData<Boolean> = SingleEventLiveData<Boolean>()
    val unauthorized: LiveData<Boolean> = _unauthorized

    fun onResponse(response: List<Artist>?): Unit {
        val list = ArrayList(_items.value)
        list.addAll(response ?: emptyList())
        _items.value = list
    }

    fun onError(error: ErrorModel?): Unit {
        if (error?.status == ErrorStatus.UNAUTHORIZED)
            _unauthorized.value = true

        error?.message?.let {
            _message.value = it
        }
    }

    fun search(query: String?) {
        if (query?.isEmpty() ?: true)
            return
        _items.value = emptyList()
        query?.let {
            searchUseCase.setParam(query, 0, 20)
                .execute(compositeDisposable, this::onResponse, this::onError)
        }
    }
}