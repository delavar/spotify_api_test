package com.delavar.digipay.data.repository

import Artist
import com.delavar.digipay.data.source.cloud.CloudSource
import com.delavar.digipay.domain.repository.SearchRepository
import io.reactivex.Flowable

class SearchRepositoryImpl(val cloudSource: CloudSource) : SearchRepository {
    override fun getResult(query: String, offset: Int, limit: Int): Flowable<List<Artist>> {
        return cloudSource.search(query, offset, limit).map { it.artists.items }
    }
}