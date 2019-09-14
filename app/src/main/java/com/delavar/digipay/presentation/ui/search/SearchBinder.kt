package com.delavar.digipay.presentation.ui.search

import com.delavar.digipay.domain.model.Artist
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.delavar.digipay.R
import com.delavar.digipay.presentation.utils.GlideApp

@BindingAdapter("app:items")
fun items(recyclerView: RecyclerView, items: List<Artist>?) {
    items?.let { (recyclerView.adapter as SearchAdapter).submitList(it) }
}


@BindingAdapter("app:img")
fun image(imageView: ImageView, source: String?) {
    GlideApp.with(imageView.context)
        .load(source)
        .placeholder(R.mipmap.ic_launcher)
        .into(imageView);
}