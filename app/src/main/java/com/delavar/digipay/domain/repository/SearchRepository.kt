package com.delavar.digipay.domain.repository

import Artist
import io.reactivex.Flowable
import java.time.ZoneOffset

interface SearchRepository {
    fun getResult(query: String, offset: Int, limit: Int): Flowable<List<Artist>>
}