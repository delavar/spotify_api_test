package com.delavar.digipay.presentation.ui.detail

import android.os.Bundle
import android.view.View
import com.delavar.digipay.R
import com.delavar.digipay.databinding.FragmentDetailBinding
import com.delavar.digipay.domain.model.Artist
import com.delavar.digipay.presentation.ui.base.BaseFragment
import com.delavar.digipay.presentation.ui.base.BaseViewModel
import com.delavar.digipay.presentation.ui.search.SearchFragment
import com.delavar.digipay.presentation.utils.ViewModelScope
import com.delavar.digipay.presentation.utils.getViewModel

class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>() {
    override val layout: Int = R.layout.fragment_detail
    override lateinit var viewModel: DetailViewModel

    override fun viewModelProvider(): DetailViewModel {
        return DetailViewModel()
    }

    companion object {
        val TAG = DetailFragment::class.java.simpleName
        fun newInstance(artist: Artist): DetailFragment {
            return DetailFragment().apply {
                arguments = Bundle()
                arguments?.putParcelable(TAG, artist)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        viewModel = getViewModel(ViewModelScope.FRAGMENT, this::viewModelProvider)
        viewModel.artist.value = arguments?.getParcelable(TAG)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
    }
}