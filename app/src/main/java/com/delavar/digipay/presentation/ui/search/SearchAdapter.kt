package com.delavar.digipay.presentation.ui.search

import com.delavar.digipay.domain.model.Artist
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.delavar.digipay.databinding.ItemArtistBinding
import com.delavar.digipay.presentation.ui.MainNavigator

class SearchAdapter(val mainNavigator: MainNavigator) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {
    private var items = emptyList<Artist>()

    fun submitList(newItems: List<Artist>) {
        val diffUtil = DiffUtil.calculateDiff(object : DiffUtil.Callback() {
            override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition].id == newItems[newItemPosition].id
            }

            override fun getOldListSize(): Int {
                return items.size
            }

            override fun getNewListSize(): Int {
                return newItems.size
            }

            override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
                return items[oldItemPosition].id == newItems[newItemPosition].id
            }
        })
        diffUtil.dispatchUpdatesTo(this)
        items = newItems.toList()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemArtistBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBind(items[position])
    }

    inner class ViewHolder(val binding: ItemArtistBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.setOnClickListener {
                mainNavigator.goToDetail(binding.artist)
            }
        }

        fun onBind(artist: Artist) {
            binding.artist = artist
        }
    }
}
