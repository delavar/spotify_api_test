package com.delavar.digipay.presentation.ui.detail

import androidx.lifecycle.MutableLiveData
import com.delavar.digipay.domain.model.Artist
import com.delavar.digipay.presentation.ui.base.BaseViewModel

class DetailViewModel : BaseViewModel() {
    val artist: MutableLiveData<Artist> = MutableLiveData<Artist>()
}