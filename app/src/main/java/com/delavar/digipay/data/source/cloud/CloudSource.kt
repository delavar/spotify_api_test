package com.delavar.digipay.data.source.cloud

import PaginateResponse
import SearchResponse
import io.reactivex.Flowable

interface CloudSource {
    fun search(
        offset: Int,
        limit: Int
    ) : Flowable<SearchResponse>
}