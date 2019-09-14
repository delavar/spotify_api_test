package com.delavar.digipay.presentation.ui.search

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.delavar.digipay.R
import com.delavar.digipay.databinding.FragmentSearchBinding
import com.delavar.digipay.presentation.di.SearchComponent
import com.delavar.digipay.presentation.ui.base.BaseFragment
import android.R.menu
import com.delavar.digipay.presentation.ui.MainActivity
import androidx.core.view.MenuItemCompat.getActionView
import android.content.Context.SEARCH_SERVICE
import androidx.core.content.ContextCompat.getSystemService
import android.app.SearchManager
import android.content.Context
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import com.delavar.digipay.presentation.ui.auth.AuthActivity
import com.delavar.digipay.presentation.utils.ViewModelScope
import com.delavar.digipay.presentation.utils.getAppComponent
import com.delavar.digipay.presentation.utils.getViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_search.*


class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>() {

    override val layout: Int = R.layout.fragment_search
    override lateinit var viewModel: SearchViewModel
    lateinit var component: SearchComponent

    override fun viewModelProvider(): SearchViewModel {
        return SearchViewModel(component.searchUseCase)
    }

    companion object {
        fun newInstance(): SearchFragment {
            return SearchFragment().apply { arguments?.putString("", "") }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        component = SearchComponent(this.requireContext().applicationContext.getAppComponent())
        viewModel = getViewModel(ViewModelScope.FRAGMENT, this::viewModelProvider)
        retainInstance = true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerView.adapter = SearchAdapter()
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.viewModel = viewModel

        viewModel.message.observe(this, object : Observer<String> {
            override fun onChanged(t: String) {
                Snackbar.make(container, t, Snackbar.LENGTH_LONG).show()
            }
        })

        viewModel.unauthorized.observe(this, object : Observer<Boolean> {
            override fun onChanged(t: Boolean) {
                AuthActivity.start(requireContext())
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.search_menu, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewModel.search(query)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })
    }
}