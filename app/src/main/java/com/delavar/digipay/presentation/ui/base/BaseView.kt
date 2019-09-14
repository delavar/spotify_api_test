package com.delavar.digipay.presentation.ui.base

import androidx.databinding.ViewDataBinding

interface BaseView<V: BaseViewModel,B : ViewDataBinding> {
    val layout: Int
    var binding: B
    var viewModel: V
}