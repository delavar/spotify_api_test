package com.delavar.digipay.presentation.ui

import androidx.appcompat.app.AppCompatActivity
import com.delavar.digipay.R
import com.delavar.digipay.domain.model.Artist
import com.delavar.digipay.presentation.ui.detail.DetailFragment
import java.lang.ref.WeakReference

class MainNavigator(activity: AppCompatActivity) {
    val mActivity: WeakReference<AppCompatActivity>

    init {
        mActivity = WeakReference(activity)
    }

    fun goToDetail(artist: Artist?) {
        if(artist==null)
            return
        mActivity.get()?.supportFragmentManager?.beginTransaction()?.replace(
            R.id.container,
            DetailFragment.newInstance(artist),
            DetailFragment.TAG
        )?.addToBackStack(DetailFragment.TAG)?.commit()
    }
}