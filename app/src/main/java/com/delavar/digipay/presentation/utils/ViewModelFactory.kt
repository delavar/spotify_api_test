package com.delavar.digipay.presentation.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider


class ViewModelFactory<T>(val provider : ()->T) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return provider.invoke() as T
    }
}