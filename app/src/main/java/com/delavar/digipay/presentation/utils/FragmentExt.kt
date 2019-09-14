package com.delavar.digipay.presentation.utils

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel?> Fragment.getViewModel(
    scope: ViewModelScope,
    noinline provider: () -> T
): T = when (scope) {
    ViewModelScope.ACTIVITY ->
        ViewModelProviders.of(this.requireActivity(), ViewModelFactory<T>(provider))[T::class.java]
    ViewModelScope.FRAGMENT ->
        ViewModelProviders.of(this, ViewModelFactory<T>(provider))[T::class.java]

}