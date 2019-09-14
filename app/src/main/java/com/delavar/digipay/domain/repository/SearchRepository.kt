package com.delavar.digipay.domain.repository

import com.delavar.digipay.domain.model.Artist
import io.reactivex.Flowable

interface SearchRepository {
    fun getResult(query: String, offset: Int, limit: Int): Flowable<List<Artist>>
}