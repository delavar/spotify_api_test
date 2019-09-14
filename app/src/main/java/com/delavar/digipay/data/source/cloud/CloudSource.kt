package com.delavar.digipay.data.source.cloud

import PaginateResponse
import SearchResponse
import io.reactivex.Flowable

interface CloudSource {
    fun search(
        query: String,
        offset: Int,
        limit: Int
    ) : Flowable<SearchResponse>
}