package com.delavar.digipay.presentation.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment<V : BaseViewModel, B : ViewDataBinding> : Fragment(), BaseView<V, B> {
    override lateinit var binding: B
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layout, container, false)
        lifecycle.addObserver(viewModel)
        binding.lifecycleOwner = this
        return binding.root
    }

    abstract fun viewModelProvider(): V

}