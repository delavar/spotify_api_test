package com.delavar.digipay.domain.repository

import Artist
import io.reactivex.Flowable
import java.time.ZoneOffset

interface SearchRepository {
    fun getResult(offset: Int, limit: Int): Flowable<List<Artist>>
}